package com.desmond.rightmove.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.desmond.rightmove.domain.GetRecipesUseCase
import com.desmond.rightmove.domain.RequestArgs
import com.desmond.rightmove.ui.model.RecipeDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor(
    private val getRecipesUseCase: GetRecipesUseCase,
) : ViewModel() {

    private val _recipesViewState =
        MutableStateFlow<RecipesViewState>(RecipesViewState.Success(emptyList()))
    val recipesViewState: StateFlow<RecipesViewState> = _recipesViewState.asStateFlow()

    fun getRecipes() {

        viewModelScope.launch(Dispatchers.Main) {
            try {
                getRecipesUseCase.execute(RequestArgs(from = 0))
                    .onStart {
                        _recipesViewState.value = RecipesViewState.Loading
                    }
                    .collect { recipes ->
                        _recipesViewState.value = RecipesViewState.Success(recipes)
                    }
            } catch (throwable: Throwable) {
                _recipesViewState.value = RecipesViewState.Error(throwable)
            }
        }
    }
}

sealed interface RecipesViewState {
    data object Loading : RecipesViewState
    data class Success(val recipes: List<RecipeDetails>) : RecipesViewState
    data class Error(val exception: Throwable) : RecipesViewState
}