package com.delicious.homeData.repository.homeRepository.fakeResponse

import com.delicious.base.response.NetworkResponse
import com.delicious.homeData.model.randomRecipe.BaseRecipeRandomResponse
import com.delicious.homeData.model.randomRecipe.RandomRecipeResponse


val fakeRandomRecipeSuccessResponse = NetworkResponse.Success(BaseRecipeRandomResponse(result = listOf(
        RandomRecipeResponse(1, "recipe 1" , "", ""),
        RandomRecipeResponse(2, "recipe 2" , "", ""),
)))

val error401Response = NetworkResponse.Error(401, "Error response body")
