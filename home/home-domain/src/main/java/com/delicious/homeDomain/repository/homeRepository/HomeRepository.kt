package com.delicious.homeDomain.repository.homeRepository

import com.delicious.base.domain.ResultState
import com.delicious.homeDomain.model.mealType.MealType
import com.delicious.homeDomain.model.popularRecipe.PopularRecipe
import com.delicious.homeDomain.model.randomRecipe.RandomRecipe

interface HomeRepository {
    suspend fun getPopularRecipe(): ResultState<List<PopularRecipe>>

    suspend fun getMealType(): ResultState<List<MealType>>

    suspend fun getRandomRecipe(): ResultState<List<RandomRecipe>>

}