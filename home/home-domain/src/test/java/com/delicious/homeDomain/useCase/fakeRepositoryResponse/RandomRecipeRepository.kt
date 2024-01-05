package com.delicious.homeDomain.useCase.fakeRepositoryResponse

import com.delicious.base.domain.ResultState
import com.delicious.homeDomain.model.mealType.MealType
import com.delicious.homeDomain.model.popularRecipe.PopularRecipe
import com.delicious.homeDomain.model.randomRecipe.RandomRecipe

val fakePopularRecipeSuccessResult: ResultState<List<PopularRecipe>> =
    ResultState.Success(listOf(PopularRecipe(1, "Recipe one" , "", "")))

val fakePopularRecipeErrorResult: ResultState.Failure =
    ResultState.Failure(401, "unauthenticated user")

val fakeRandomRecipeSuccessResult : ResultState.Success<List<RandomRecipe>> =
        ResultState.Success(listOf(RandomRecipe(1, "Recipe one" , 0, "" , 10)))

val fakeMealTypesSuccessResult : ResultState.Success<List<MealType>> =
    ResultState.Success(listOf(MealType(0,0)))