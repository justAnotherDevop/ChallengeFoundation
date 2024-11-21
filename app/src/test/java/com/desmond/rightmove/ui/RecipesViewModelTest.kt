package com.desmond.rightmove.ui

import app.cash.turbine.test
import com.desmond.rightmove.domain.GetRecipesUseCase
import com.desmond.rightmove.testutil.StandardDispatcherRule
import com.desmond.rightmove.ui.model.RecipeDetails
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@OptIn(ExperimentalCoroutinesApi::class)
@ExtendWith(StandardDispatcherRule::class)
class RecipesViewModelTest {

    @RelaxedMockK
    private lateinit var getRecipesUseCase: GetRecipesUseCase

    private lateinit var sut: RecipesViewModel

    @BeforeEach
    fun setup() {
        MockKAnnotations.init(this)
        sut = RecipesViewModel(
            getRecipesUseCase = getRecipesUseCase
        )
    }

    @Test
    fun `given recipes are returned successfully, when getRecipes is called, then view state is updated in the expected order`() =
        runTest {
            val result = listOf(mockk<RecipeDetails>())

            every { getRecipesUseCase.execute(any()) } returns flowOf(result)

            sut.recipesViewState.test {
                sut.getRecipes()

                assertEquals(RecipesViewState.Success(emptyList()), awaitItem())
                assertEquals(RecipesViewState.Loading, awaitItem())
                assertEquals(RecipesViewState.Success(result), awaitItem())
            }
        }

    @Test
    fun `given recipes are not returned successfully, when getRecipes is called, then view state is update with error state`() = runTest {
        val mockException = Throwable("Test exception")
        every { getRecipesUseCase.execute(any()) } throws mockException


        val stateUpdateResults = mutableListOf<RecipesViewState>()
        val job = launch(UnconfinedTestDispatcher()) {
            sut.recipesViewState.toList(stateUpdateResults)
        }

        sut.getRecipes()

        testScheduler.advanceUntilIdle()

        assertEquals(
            listOf(
                RecipesViewState.Success(emptyList()),
                RecipesViewState.Error(mockException)
            ),
            stateUpdateResults
        )

        job.cancel()
    }
}