package com.desmond.rightmove.data.mappers

import com.desmond.rightmove.data.model.Instruction
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class InstructionsToDomainMapperTest {
    private val sut = InstructionsToDomainMapper()

    @Test
    fun `when map is invoked, then instruction text is mapped as expected`() {
        val instructionText = "Test instructions"
        val instruction = mockk<Instruction>(relaxed = true).apply {
            every { this@apply.displayText } returns instructionText
        }

        val result = sut.map(instruction).instructionText

        assertEquals(instructionText, result)
    }

    @Test
    fun `when map is invoked, position is mapped as expected`() {
        val position = 1
        val instruction = mockk<Instruction>(relaxed = true).apply {
            every { this@apply.position } returns position
        }

        val result = sut.map(instruction).position

        assertEquals(position, result)
    }
}