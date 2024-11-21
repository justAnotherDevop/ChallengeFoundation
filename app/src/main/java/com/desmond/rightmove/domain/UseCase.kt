package com.desmond.rightmove.domain

import com.desmond.rightmove.core.mappers.DomainModel
import com.desmond.rightmove.core.mappers.PresentationModel
import kotlinx.coroutines.flow.Flow

interface UseCase<IN: DomainModel, OUT: PresentationModel> {
    suspend fun execute(requestArgs: IN): OUT
}

interface FlowUseCase<IN: DomainModel, OUT: PresentationModel> {
    fun execute(requestArgs: IN): Flow<OUT>
}

interface FlowIterableUseCase<IN: DomainModel, OUT: List<PresentationModel>> {
    fun execute(requestArgs: IN): Flow<OUT>
}
