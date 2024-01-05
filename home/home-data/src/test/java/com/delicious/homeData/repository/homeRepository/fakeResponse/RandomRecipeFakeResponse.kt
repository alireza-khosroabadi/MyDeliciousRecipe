package com.delicious.homeData.repository.homeRepository.fakeResponse

import com.delicious.base.response.NetworkResponse
import com.delicious.homeData.model.popularRecipe.BasePopularRecipeResponse
import com.delicious.homeData.model.popularRecipe.PopularRecipeResponse
import com.delicious.homeData.model.randomRecipe.BaseRandomRecipeResponse
import com.delicious.homeData.model.randomRecipe.RandomRecipeResponse


val fakePopularRecipeSuccessResponse = NetworkResponse.Success(BasePopularRecipeResponse(result = listOf(
        PopularRecipeResponse(1, "recipe 1" , "", ""),
        PopularRecipeResponse(2, "recipe 2" , "", ""),
)))

val fakeRandomRecipeSuccessResponse = NetworkResponse.Success(BaseRandomRecipeResponse(listOf(
        RandomRecipeResponse(id = 1, title = "random 1", 0,"", 0),
        RandomRecipeResponse(id = 1, title = "random 2", 0,"", 0)
)))


val error401Response = NetworkResponse.Error(401, "Error response body")
