package com.desmond.rightmove.data

import com.desmond.rightmove.data.mappers.RecipeToDomainMapper
import com.desmond.rightmove.domain.model.RecipeDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RecipesRepository @Inject constructor(
    private val recipesService: RecipesService,
    private val recipeToDomainMapper: RecipeToDomainMapper
) {

    fun getRecipes(
        from: Int,
        size: Int = DEFAULT_PAGE_SIZE
    ): Flow<List<RecipeDomainModel>> = flow {
        val recipes = recipesService.getRecipes(
            start = from,
            size = size
        ).results.map { recipe ->
            recipeToDomainMapper.map(recipe)
        }
        emit(recipes)
    }

    companion object {
        private const val DEFAULT_PAGE_SIZE = 50
    }
}