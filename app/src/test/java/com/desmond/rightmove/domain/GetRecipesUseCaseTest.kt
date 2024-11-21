package com.desmond.rightmove.domain

import com.desmond.rightmove.data.RecipesRepository
import com.desmond.rightmove.domain.mappers.RecipeDomainToPresentationMapper
import com.desmond.rightmove.domain.model.RecipeDomainModel
import com.desmond.rightmove.testutil.UnconfinedDispatcherRule
import com.desmond.rightmove.ui.model.RecipeDetails
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class GetRecipesUseCaseTest {

    @get:Rule
    val unconfinedDispatcherRule = UnconfinedDispatcherRule()

    @RelaxedMockK
    private lateinit var repository: RecipesRepository

    @RelaxedMockK
    private lateinit var recipeDomainToPresentationMapper: RecipeDomainToPresentationMapper

    private lateinit var sut: GetRecipesUseCase

    @BeforeEach
    fun setup() {
        MockKAnnotations.init(this)
        sut = GetRecipesUseCase(
            recipesRepository = repository,
            recipeToPresentationMapper = recipeDomainToPresentationMapper,
            ioDispatcher = unconfinedDispatcherRule.dispatcher,
        )
    }

    @Test
    fun `when execute is called, then repository is invoked with expected arguments to get recipes`() {
        val requestArgs = RequestArgs(
            from = 0
        )
        sut.execute(requestArgs)

        verify(exactly = 1) {
            repository.getRecipes(requestArgs.from)
        }
    }

    @Test
    fun `given recipes are retrieved successfully from repository, when execute is called, then expected recipes are returned`() =
        runTest {
            val requestArgs = RequestArgs(
                from = 0
            )
            val recipe = mockk<RecipeDomainModel>()
            val recipes = listOf(recipe)
            val expectedRecipe = mockk<RecipeDetails>()

            val expected = listOf(expectedRecipe)

            every { repository.getRecipes(requestArgs.from) } returns flowOf(recipes)
            every { recipeDomainToPresentationMapper.map(recipe) } returns expectedRecipe

            sut.execute(requestArgs).collect { results ->
                Assertions.assertEquals(expected, results)
            }
        }

    @Test
    fun `given recipes are not retrieved successfully from repository and error is returned, when execute is called, then propagate error`() {
        val requestArgs = RequestArgs(
            from = 0
        )

        every { repository.getRecipes(requestArgs.from) } throws Throwable("Test Error")

        assertThrows<Throwable> {
            sut.execute(requestArgs)
        }
    }
}