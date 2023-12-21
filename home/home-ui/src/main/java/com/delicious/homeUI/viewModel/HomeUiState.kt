package com.delicious.homeUI.viewModel

import com.delicious.homeDomain.model.randomRecipe.RandomRecipe


sealed interface HomeUiState {
    val isLoading: Boolean
    val hasError: Boolean
    val errorMessage: String

    data object Loading : HomeUiState {
        override val isLoading: Boolean
            get() = true
        override val hasError: Boolean
            get() = false
        override val errorMessage: String
            get() = ""
    }

    data class Error(
        val errorCode: Int,
        override val errorMessage: String,
        override val isLoading: Boolean = false,
        override val hasError: Boolean = true
    ) : HomeUiState

    data class RandomRecipes(
        val data: List<RandomRecipe>,
        override val isLoading: Boolean = false,
        override val hasError: Boolean = false,
        override val errorMessage: String = ""
    ) : HomeUiState
}