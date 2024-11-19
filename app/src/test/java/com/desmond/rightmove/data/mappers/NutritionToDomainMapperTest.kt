package com.desmond.rightmove.data.mappers

import com.desmond.rightmove.data.model.Nutrition
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class NutritionToDomainMapperTest {

    private val sut = NutritionToDomainMapper()

    @Test
    fun `when map is invoked, then calories is mapped as expected`() {
        val calories = 5
        val nutrition = mockk<Nutrition>(relaxed = true).apply {
            every { this@apply.calories } returns calories
        }

        val result = sut.map(nutrition).calories

        assertEquals(calories, result)
    }

    @Test
    fun `when map is invoked, then carbohydrates is mapped as expected`() {
        val carbohydrates = 5
        val nutrition = mockk<Nutrition>(relaxed = true).apply {
            every { this@apply.carbohydrates } returns carbohydrates
        }

        val result = sut.map(nutrition).carbohydrates

        assertEquals(carbohydrates, result)
    }

    @Test
    fun `when map is invoked, then protein is mapped as expected`() {
        val protein = 5
        val nutrition = mockk<Nutrition>(relaxed = true).apply {
            every { this@apply.protein } returns protein
        }

        val result = sut.map(nutrition).protein

        assertEquals(protein, result)
    }

    @Test
    fun `when map is invoked, then fat is mapped as expected`() {
        val fat = 5
        val nutrition = mockk<Nutrition>(relaxed = true).apply {
            every { this@apply.fat } returns fat
        }

        val result = sut.map(nutrition).fat

        assertEquals(fat, result)
    }

    @Test
    fun `when map is invoked, then fiber is mapped as expected`() {
        val fiber = 5
        val nutrition = mockk<Nutrition>(relaxed = true).apply {
            every { this@apply.fiber } returns fiber
        }

        val result = sut.map(nutrition).fiber

        assertEquals(fiber, result)
    }

    @Test
    fun `when map is invoked, then sugar is mapped as expected`() {
        val sugar = 5
        val nutrition = mockk<Nutrition>(relaxed = true).apply {
            every { this@apply.sugar } returns sugar
        }

        val result = sut.map(nutrition).sugar

        assertEquals(sugar, result)
    }
}