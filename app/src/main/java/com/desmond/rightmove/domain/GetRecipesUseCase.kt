package com.desmond.rightmove.domain

import com.desmond.rightmove.core.di.qualifiers.IoDispatcher
import com.desmond.rightmove.core.mappers.DomainModel
import com.desmond.rightmove.data.RecipesRepository
import com.desmond.rightmove.domain.mappers.RecipeDomainToPresentationMapper
import com.desmond.rightmove.ui.model.RecipeDetails
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetRecipesUseCase @Inject constructor(
    private val recipesRepository: RecipesRepository,
    private val recipeToPresentationMapper: RecipeDomainToPresentationMapper,
    @IoDispatcher val ioDispatcher: CoroutineDispatcher,
) : FlowIterableUseCase<RequestArgs, List<RecipeDetails>> {

    override fun execute(requestArgs: RequestArgs): Flow<List<RecipeDetails>> {
        return recipesRepository.getRecipes(
            from = requestArgs.from,
        ).map { recipes ->
            recipes.map(
                recipeToPresentationMapper::map
            )
        }
            .flowOn(ioDispatcher)
    }
}

data class RequestArgs(
    val from: Int,
) : DomainModel