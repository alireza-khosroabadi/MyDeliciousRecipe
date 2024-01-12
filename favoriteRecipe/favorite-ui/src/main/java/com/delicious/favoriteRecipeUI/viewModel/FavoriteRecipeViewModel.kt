package com.delicious.favoriteRecipeUI.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.delicious.base.domain.ResultState
import com.delicious.favoriteDomain.model.FavoriteRecipe
import com.delicious.favoriteDomain.useCase.FavoriteRecipesUseCase
import com.delicious.favoriteDomain.useCase.RemoveFavoriteRecipeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteRecipeViewModel
    @Inject
    constructor(
        private val favoriteRecipesUseCase: FavoriteRecipesUseCase,
        private val removeFavoriteRecipeUseCase: RemoveFavoriteRecipeUseCase,
    ) : ViewModel() {
        val uiState =
            favoriteRecipesUseCase.invoke()
                .map { data ->
                    when (data) {
                        is ResultState.Exception -> FavoriteUiState.Error(data.error)
                        is ResultState.Failure -> FavoriteUiState.Error(Exception(data.error))
                        is ResultState.Success -> FavoriteUiState.FavoriteRecipes(data.data)
                    }
                }
                .stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(5_000),
                    initialValue = FavoriteUiState.Loading,
                )

        fun removeFavoriteRecipe(recipe: FavoriteRecipe) {
            viewModelScope.launch {
                removeFavoriteRecipeUseCase.invoke(recipe)
            }
        }
    }
