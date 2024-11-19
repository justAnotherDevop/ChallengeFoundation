package com.desmond.rightmove.domain

import com.desmond.rightmove.core.mappers.DomainModel
import com.desmond.rightmove.core.mappers.PresentationModel

interface UseCase<IN: DomainModel, OUT: PresentationModel> {
    suspend fun execute(requestArgs: IN): OUT
}
