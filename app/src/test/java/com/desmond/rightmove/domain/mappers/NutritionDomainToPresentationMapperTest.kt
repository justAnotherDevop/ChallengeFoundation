package com.desmond.rightmove.domain.mappers

import com.desmond.rightmove.domain.model.NutritionDomainModel
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class NutritionDomainToPresentationMapperTest {

    private val sut = NutritionDomainToPresentationMapper()

    @Test
    fun `when map is invoked, then calories is mapped as expected`() {
        val calories = 5
        val nutritionDomainModel = mockk<NutritionDomainModel>(relaxed = true).apply {
            every { this@apply.calories } returns calories
        }

        val result = sut.map(nutritionDomainModel).calories

        assertEquals(calories, result)
    }

    @Test
    fun `when map is invoked, then carbohydrates is mapped as expected`() {
        val carbohydrates = 5
        val nutritionDomainModel = mockk<NutritionDomainModel>(relaxed = true).apply {
            every { this@apply.carbohydrates } returns carbohydrates
        }

        val result = sut.map(nutritionDomainModel).carbohydrates

        assertEquals(carbohydrates, result)
    }

    @Test
    fun `when map is invoked, then protein is mapped as expected`() {
        val protein = 5
        val nutritionDomainModel = mockk<NutritionDomainModel>(relaxed = true).apply {
            every { this@apply.protein } returns protein
        }

        val result = sut.map(nutritionDomainModel).protein

        assertEquals(protein, result)
    }

    @Test
    fun `when map is invoked, then fat is mapped as expected`() {
        val fat = 5
        val nutritionDomainModel = mockk<NutritionDomainModel>(relaxed = true).apply {
            every { this@apply.fat } returns fat
        }

        val result = sut.map(nutritionDomainModel).fat

        assertEquals(fat, result)
    }

    @Test
    fun `when map is invoked, then fiber is mapped as expected`() {
        val fiber = 5
        val nutritionDomainModel = mockk<NutritionDomainModel>(relaxed = true).apply {
            every { this@apply.fiber } returns fiber
        }

        val result = sut.map(nutritionDomainModel).fiber

        assertEquals(fiber, result)
    }

    @Test
    fun `when map is invoked, then sugar is mapped as expected`() {
        val sugar = 5
        val nutritionDomainModel = mockk<NutritionDomainModel>(relaxed = true).apply {
            every { this@apply.sugar } returns sugar
        }

        val result = sut.map(nutritionDomainModel).sugar

        assertEquals(sugar, result)
    }
}