package com.delicious.favoriteRecipeUI.viewModel

import com.delicious.favoriteDomain.model.FavoriteRecipe

sealed interface FavoriteUiState {
    data object Loading : FavoriteUiState

    data class Error(val exception: Throwable) : FavoriteUiState

    data class FavoriteRecipes(val data: List<FavoriteRecipe>) : FavoriteUiState
}
