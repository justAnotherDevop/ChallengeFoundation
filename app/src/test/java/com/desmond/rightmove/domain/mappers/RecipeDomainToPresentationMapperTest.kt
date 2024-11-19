package com.desmond.rightmove.domain.mappers

import com.desmond.rightmove.domain.model.InstructionsDomainModel
import com.desmond.rightmove.domain.model.NutritionDomainModel
import com.desmond.rightmove.domain.model.RecipeDomainModel
import com.desmond.rightmove.ui.model.RecipeInstruction
import com.desmond.rightmove.ui.model.RecipeNutrition
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class RecipeDomainToPresentationMapperTest {

    @RelaxedMockK
    private lateinit var instructionToPresentationMapper: InstructionDomainToPresentationMapper

    @RelaxedMockK
    private lateinit var nutritionToPresentationMapper: NutritionDomainToPresentationMapper

    private lateinit var sut: RecipeDomainToPresentationMapper

    @BeforeEach
    fun setup() {
        MockKAnnotations.init(this)
        sut = RecipeDomainToPresentationMapper(
            instructionToPresentationMapper = instructionToPresentationMapper,
            nutritionToPresentationMapper = nutritionToPresentationMapper
        )
    }

    @Test
    fun `given recipe domain model, when map is invoked, then recipeId is mapped as expected`() {
        val id = 123
        val recipeDomain = mockk<RecipeDomainModel>(relaxed = true).apply {
            every { recipeId } returns id
        }

        val result = sut.map(recipeDomain).recipeId

        assertEquals(id, result)
    }

    @Test
    fun `given recipe domain model, when map is invoked, then name is mapped as expected`() {
        val recipeName = "recipe1"
        val recipeDomain = mockk<RecipeDomainModel>(relaxed = true).apply {
            every { name } returns recipeName
        }

        val result = sut.map(recipeDomain).name

        assertEquals(recipeName, result)
    }

    @Test
    fun `given recipe domain model, when map is invoked, then cook time is mapped as expected`() {
        val cookTimeInMinutes = 15
        val recipeDomain = mockk<RecipeDomainModel>(relaxed = true).apply {
            every { this@apply.cookTimeInMinutes } returns cookTimeInMinutes
        }

        val result = sut.map(recipeDomain).cookTimeInMinutes

        assertEquals(cookTimeInMinutes, result)
    }

    @Test
    fun `given recipe domain model, when map is invoked, then description is mapped as expected`() {
        val description = "This is a test description"
        val recipeDomain = mockk<RecipeDomainModel>(relaxed = true).apply {
            every { this@apply.description } returns description
        }

        val result = sut.map(recipeDomain).description

        assertEquals(description, result)
    }

    @Test
    fun `given recipe domain model, when map is invoked, then instructions are mapped as expected`() {
        val instructionDomainModel = mockk<InstructionsDomainModel>()
        val instructionsDomainModel = listOf(instructionDomainModel)
        val recipeInstruction = mockk<RecipeInstruction>()
        val recipeInstructions = listOf(recipeInstruction)
        val recipeDomain = mockk<RecipeDomainModel>(relaxed = true).apply {
            every { this@apply.instructions } returns instructionsDomainModel
        }

        every { instructionToPresentationMapper.map(instructionDomainModel) } returns recipeInstruction

        val result = sut.map(recipeDomain).instructions

        assertEquals(recipeInstructions, result)
    }

    @Test
    fun `given recipe domain model, when map is invoked, then prepTimeInMinutes is mapped as expected`() {
        val prepTime = 15
        val recipeDomain = mockk<RecipeDomainModel>(relaxed = true).apply {
            every { prepTimeInMinutes } returns prepTime
        }

        val result = sut.map(recipeDomain).prepTimeInMinutes

        assertEquals(prepTime, result)
    }

    @Test
    fun `given recipe has at most 1 serving, when map is invoked, then singular serving text will apply`() {
        val servingsCount = 1
        val servingsSingularText = "Serving"
        val servingsPluralText = "Servings"

        val expected = "$servingsCount $servingsSingularText"

        val recipeDomain = mockk<RecipeDomainModel>(relaxed = true).apply {
            every { numServings } returns servingsCount
            every { servingsNounSingular } returns servingsSingularText
            every { servingsNounPlural } returns servingsPluralText
        }

        val result = sut.map(recipeDomain).servingsText

        assertEquals(expected, result)
    }

    @Test
    fun `given recipe has at more than 1 serving, when map is invoked, then plural serving text will apply`() {
        val servingsCount = 3
        val servingsSingularText = "Serving"
        val servingsPluralText = "Servings"

        val expected = "$servingsCount $servingsPluralText"

        val recipeDomain = mockk<RecipeDomainModel>(relaxed = true).apply {
            every { numServings } returns servingsCount
            every { servingsNounSingular } returns servingsSingularText
            every { servingsNounPlural } returns servingsPluralText
        }

        val result = sut.map(recipeDomain).servingsText

        assertEquals(expected, result)
    }

    @Test
    fun `given recipe domain model, when map is invoked, then thumbnail is mapped as expected`() {
        val thumbnail = "www.url.com"
        val recipeDomain = mockk<RecipeDomainModel>(relaxed = true).apply {
            every { thumbnailUrl } returns thumbnail
        }

        val result = sut.map(recipeDomain).thumbnailUrl

        assertEquals(thumbnail, result)
    }

    @Test
    fun `given recipe domain model, when map is invoked, then positive rating is mapped as expected`() {
        val rating = 1000
        val recipeDomain = mockk<RecipeDomainModel>(relaxed = true).apply {
            every { positiveRating } returns rating
        }

        val result = sut.map(recipeDomain).positiveRating

        assertEquals(rating, result)
    }

    @Test
    fun `given recipe domain model, when map is invoked, then negative rating is mapped as expected`() {
        val rating = 1000
        val recipeDomain = mockk<RecipeDomainModel>(relaxed = true).apply {
            every { negativeRating } returns rating
        }

        val result = sut.map(recipeDomain).negativeRating

        assertEquals(rating, result)
    }

    @Test
    fun `given recipe domain model, when map is invoked, then nutrition is mapped as expected`() {
        val nutritionDomainModel = mockk<NutritionDomainModel>()
        val recipeNutrition = mockk<RecipeNutrition>()
        val recipeDomain = mockk<RecipeDomainModel>(relaxed = true).apply {
            every { nutrition } returns nutritionDomainModel
        }

        every { nutritionToPresentationMapper.map(nutritionDomainModel) } returns recipeNutrition

        val result = sut.map(recipeDomain).nutrition

        assertEquals(recipeNutrition, result)
    }
}