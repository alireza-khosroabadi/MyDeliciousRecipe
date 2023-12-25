package com.delicious.homeUI.viewModel.fake

import com.delicious.base.domain.ResultState
import com.delicious.homeDomain.model.mealType.MealType
import com.delicious.homeDomain.model.popularRecipe.PopularRecipe
import com.delicious.homeDomain.model.randomRecipe.RandomRecipe

val exception = NullPointerException("null value")
val fakePopularRecipesSuccessResult = ResultState.Success(listOf(
    PopularRecipe(1, "Recepie one" , "", ""),
    PopularRecipe(2, "Recepie two" , "", "")
))

val fakeRandomRecipesSuccessResult = ResultState.Success(listOf(
    RandomRecipe(1, "Recepie one" , 0,"", 0),
    RandomRecipe(2, "Recepie two" , 1, "", 1)
))

val fakeMealTypesSuccessResult = ResultState.Success(listOf(
    MealType(0,0),
    MealType(1,1)
))

val fakeRandomRecipesFailureResult = ResultState.Failure(401 , "user unauthenticated")

val fakeRandomRecipeExceptionResult = ResultState.Exception(exception)
