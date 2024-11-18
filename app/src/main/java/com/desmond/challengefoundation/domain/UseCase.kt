package com.desmond.challengefoundation.domain

import com.desmond.challengefoundation.core.mappers.DomainModel
import com.desmond.challengefoundation.core.mappers.PresentationModel

interface UseCase<IN: DomainModel, OUT: PresentationModel> {
    suspend fun execute(requestArgs: IN): OUT
}
