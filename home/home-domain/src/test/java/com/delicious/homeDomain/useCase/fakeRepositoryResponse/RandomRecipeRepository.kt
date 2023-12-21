package com.delicious.homeDomain.useCase.fakeRepositoryResponse

import com.delicious.base.domain.ResultState
import com.delicious.homeDomain.model.randomRecipe.RandomRecipe

val fakeRandomRecipeSuccessResult: ResultState<List<RandomRecipe>> =
    ResultState.Success(listOf(RandomRecipe(1, "Recipe one" , "", "")))

val fakeRandomRecipeErrorResult: ResultState.Failure =
    ResultState.Failure(401, "unauthenticated user")