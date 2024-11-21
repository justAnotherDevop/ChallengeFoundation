package com.desmond.rightmove.data

import com.desmond.rightmove.data.mappers.RecipeToDomainMapper
import com.desmond.rightmove.data.model.Recipe
import com.desmond.rightmove.data.model.RecipesResponse
import com.desmond.rightmove.domain.model.RecipeDomainModel
import com.desmond.rightmove.testutil.UnconfinedDispatcherRule

import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class RecipesRepositoryTest {

    @get:Rule
    val unconfinedDispatcherRule = UnconfinedDispatcherRule()

    @RelaxedMockK
    private lateinit var recipesService: RecipesService

    @RelaxedMockK
    private lateinit var recipeToDomainMapper: RecipeToDomainMapper

    private lateinit var sut: RecipesRepository

    @BeforeEach
    fun setup() {
        MockKAnnotations.init(this)
        sut = RecipesRepository(
            recipesService = recipesService,
            recipeToDomainMapper = recipeToDomainMapper,
        )
    }

    @Test
    fun `when getRecipes is called, then service is invoked to fetch recipes with expected arguments`() =
        runTest {
            val startIndex = 0

            sut.getRecipes(from = startIndex).collect()

            coVerify(exactly = 1) {
                recipesService.getRecipes(startIndex, any())
            }

        }

    @Test
    fun `given recipes are fetched successfully, when getRecipes is called, then recipes are mapped to domain model and returned`() =
        runTest {
            val startIndex = 0
            val recipe = mockk<Recipe>()
            val recipes = listOf(recipe)
            val response = mockk<RecipesResponse>().apply {
                every { results } returns recipes
            }
            val recipeDomainModel = mockk<RecipeDomainModel>()
            val expected = listOf(recipeDomainModel)

            coEvery { recipesService.getRecipes(start = startIndex, size = any()) } returns response
            every { recipeToDomainMapper.map(recipe) } returns recipeDomainModel

            sut.getRecipes(0).collect { result ->
                assertEquals(expected, result)
            }
        }

    @Test
    fun `given recipes are not fetched successfully, when getRecipes is called, then exception is thrown`() =
        runTest {
            coEvery {
                recipesService.getRecipes(
                    start = any(),
                    size = any()
                )
            } throws Throwable("Test exception")

            assertThrows<Throwable> {
                sut.getRecipes(0).collect()
            }
        }
}