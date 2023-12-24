package com.delicious.homeDomain.useCase.fakeRepositoryResponse

import com.delicious.base.domain.ResultState
import com.delicious.homeDomain.model.popularRecipe.PopularRecipe

val fakePopularRecipeSuccessResult: ResultState<List<PopularRecipe>> =
    ResultState.Success(listOf(PopularRecipe(1, "Recipe one" , "", "")))

val fakeRandomRecipeErrorResult: ResultState.Failure =
    ResultState.Failure(401, "unauthenticated user")