package com.delicious.homeUI.viewModel.fake

import com.delicious.base.domain.ResultState
import com.delicious.homeDomain.model.randomRecipe.RandomRecipe

val fakeRandomRecipesSuccessResult = ResultState.Success(listOf(
    RandomRecipe(1, "Recepie one" , "", ""),
    RandomRecipe(2, "Recepie two" , "", "")
))

val fakeRandomRecipesFailureResult = ResultState.Failure(401 , "user unauthenticated")

val exception = NullPointerException("null value")
val fakeRandomRecipeExceptionResult = ResultState.Exception(exception)
