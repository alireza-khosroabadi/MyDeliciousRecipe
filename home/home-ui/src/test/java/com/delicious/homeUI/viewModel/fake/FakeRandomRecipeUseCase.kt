package com.delicious.homeUI.viewModel.fake

import com.delicious.base.domain.ResultState
import com.delicious.homeDomain.model.popularRecipe.PopularRecipe

val fakePopularRecipesSuccessResult = ResultState.Success(listOf(
    PopularRecipe(1, "Recepie one" , "", ""),
    PopularRecipe(2, "Recepie two" , "", "")
))

val fakeRandomRecipesFailureResult = ResultState.Failure(401 , "user unauthenticated")

val exception = NullPointerException("null value")
val fakeRandomRecipeExceptionResult = ResultState.Exception(exception)
