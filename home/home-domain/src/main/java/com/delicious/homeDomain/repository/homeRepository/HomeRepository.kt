package com.delicious.homeDomain.repository.homeRepository

import com.delicious.base.domain.ResultState
import com.delicious.homeDomain.model.randomRecipe.RandomRecipe

interface HomeRepository {
    suspend fun getRandomRecipe(): ResultState<List<RandomRecipe>>

}