package com.delicious.homeUI.viewModel

import com.delicious.homeDomain.model.mealType.MealType
import com.delicious.homeDomain.model.popularRecipe.PopularRecipe
import com.delicious.homeDomain.model.randomRecipe.RandomRecipe


sealed interface PopularRecipeUiState{
    data object Loading:PopularRecipeUiState
    data class Error(val errorCode: Int, val message: String):PopularRecipeUiState
    data class PopularRecipes(val data: List<PopularRecipe>,):PopularRecipeUiState
}

sealed interface MealTypeUiState{
    data object Loading:MealTypeUiState
    data class Error(val errorCode: Int, val message: String):MealTypeUiState
    data class MealTypes(val mealTypes: List<MealType>): MealTypeUiState
}

sealed interface RandomRecipeUiState{
    data object Loading:RandomRecipeUiState
    data class Error(val errorCode: Int, val message: String):RandomRecipeUiState
    data class RandomRecipes(val randomRecipes: List<RandomRecipe>): RandomRecipeUiState
}