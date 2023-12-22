package com.delicious.homeDomain.repository.homeRepository

import com.delicious.base.domain.ResultState
import com.delicious.homeDomain.model.mealType.MealType
import com.delicious.homeDomain.model.popularRecipe.PopularRecipe

interface HomeRepository {
    suspend fun getRandomRecipe(): ResultState<List<PopularRecipe>>

    suspend fun getMealType(): ResultState<List<MealType>>

}