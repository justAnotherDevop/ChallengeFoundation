package com.desmond.rightmove.domain.mappers

import com.desmond.rightmove.domain.model.InstructionsDomainModel
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class InstructionDomainToPresentationMapperTest {

    private val sut = InstructionDomainToPresentationMapper()

    @Test
    fun `when map is invoked, then instruction text is mapped as expected`() {
        val instructionText = "Test instructions"
        val instructionDomainModel = mockk<InstructionsDomainModel>(relaxed = true).apply {
            every { this@apply.instructionText } returns instructionText
        }

        val result = sut.map(instructionDomainModel).instructionText

        assertEquals(instructionText, result)
    }
}