package com.desmond.rightmove.data.mappers

import com.desmond.rightmove.data.model.Instruction
import com.desmond.rightmove.data.model.Nutrition
import com.desmond.rightmove.data.model.Recipe
import com.desmond.rightmove.domain.model.InstructionsDomainModel
import com.desmond.rightmove.domain.model.NutritionDomainModel
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class RecipeToDomainMapperTest {

    @RelaxedMockK
    private lateinit var instructionsToDomainMapper: InstructionsToDomainMapper

    @RelaxedMockK
    private lateinit var nutritionToDomainMapper: NutritionToDomainMapper

    private lateinit var sut: RecipeToDomainMapper

    @BeforeEach
    fun setup() {
        MockKAnnotations.init(this)
        sut = RecipeToDomainMapper(
            instructionsToDomainMapper = instructionsToDomainMapper,
            nutritionToDomainMapper = nutritionToDomainMapper,
        )
    }

    @Test
    fun `when map is invoked, then recipeId is mapped as expected`() {
        val id = 123
        val recipe = mockk<Recipe>(relaxed = true).apply {
            every { this@apply.id } returns id
        }

        val result = sut.map(recipe).recipeId

        assertEquals(id, result)
    }

    @Test
    fun `when map is invoked, then name is mapped as expected`() {
        val recipeName = "recipe1"
        val recipe = mockk<Recipe>(relaxed = true).apply {
            every { name } returns recipeName
        }

        val result = sut.map(recipe).name

        assertEquals(recipeName, result)
    }

    @Test
    fun `when map is invoked, then cook time is mapped as expected`() {
        val cookTimeInMinutes = 15
        val recipe = mockk<Recipe>(relaxed = true).apply {
            every { cookTimeMinutes } returns cookTimeInMinutes
        }

        val result = sut.map(recipe).cookTimeInMinutes

        assertEquals(cookTimeInMinutes, result)
    }

    @Test
    fun `when map is invoked, then description is mapped as expected`() {
        val description = "This is a test description"
        val recipe = mockk<Recipe>(relaxed = true).apply {
            every { this@apply.description } returns description
        }

        val result = sut.map(recipe).description

        assertEquals(description, result)
    }

    @Test
    fun `when map is invoked, then instructions are mapped as expected`() {
        val instructionDomainModel = mockk<InstructionsDomainModel>()
        val instructionsDomainModel = listOf(instructionDomainModel)
        val instruction = mockk<Instruction>()
        val instructions = listOf(instruction)
        val recipe = mockk<Recipe>(relaxed = true).apply {
            every { this@apply.instructions } returns instructions
        }

        every { instructionsToDomainMapper.map(instruction) } returns instructionDomainModel

        val result = sut.map(recipe).instructions

        assertEquals(instructionsDomainModel, result)
    }

    @Test
    fun `when map is invoked, then prepTimeInMinutes is mapped as expected`() {
        val prepTime = 15
        val recipe = mockk<Recipe>(relaxed = true).apply {
            every { prepTimeMinutes } returns prepTime
        }

        val result = sut.map(recipe).prepTimeInMinutes

        assertEquals(prepTime, result)
    }

    @Test
    fun `when map is invoked, then thumbnail is mapped as expected`() {
        val thumbnail = "www.url.com"
        val recipe = mockk<Recipe>(relaxed = true).apply {
            every { thumbnailUrl } returns thumbnail
        }

        val result = sut.map(recipe).thumbnailUrl

        assertEquals(thumbnail, result)
    }

    @Test
    fun `when map is invoked, then positive rating is mapped as expected`() {
        val rating = 1000
        val recipe = mockk<Recipe>(relaxed = true).apply {
            every { userRatings.countPositive } returns rating
        }

        val result = sut.map(recipe).positiveRating

        assertEquals(rating, result)
    }

    @Test
    fun `when map is invoked, then negative rating is mapped as expected`() {
        val rating = 1000
        val recipe = mockk<Recipe>(relaxed = true).apply {
            every { userRatings.countNegative } returns rating
        }

        val result = sut.map(recipe).negativeRating

        assertEquals(rating, result)
    }

    @Test
    fun `when map is invoked, then nutrition is mapped as expected`() {
        val nutritionDomainModel = mockk<NutritionDomainModel>()
        val nutrition = mockk<Nutrition>()
        val recipe = mockk<Recipe>(relaxed = true).apply {
            every { this@apply.nutrition } returns nutrition
        }

        every { nutritionToDomainMapper.map(nutrition) } returns nutritionDomainModel

        val result = sut.map(recipe).nutrition

        assertEquals(nutritionDomainModel, result)
    }
}