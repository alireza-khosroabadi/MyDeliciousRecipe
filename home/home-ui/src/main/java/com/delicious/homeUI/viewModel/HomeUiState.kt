package com.delicious.homeUI.viewModel

import com.delicious.homeDomain.model.randomRecipe.RandomRecipe
import javax.annotation.concurrent.Immutable

//@Immutable
//data class HomeUiState(
//    val isLoading: Boolean = false,
//    val errorMessage: String? = null,
//    val randomRecipes: List<RandomRecipe> = emptyList()
//)

sealed interface HomeUiState{
    data object Loading: HomeUiState
    data class ErrorMessage(val error:String): HomeUiState

    data class randomRecipes(val data :List<RandomRecipe>): HomeUiState
}