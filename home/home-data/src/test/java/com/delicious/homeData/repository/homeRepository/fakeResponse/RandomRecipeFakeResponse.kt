package com.delicious.homeData.repository.homeRepository.fakeResponse

import com.delicious.base.response.NetworkResponse
import com.delicious.homeData.model.popularRecipe.BasePopularRecipeResponse
import com.delicious.homeData.model.popularRecipe.PopularRecipeResponse


val fakeRandomRecipeSuccessResponse = NetworkResponse.Success(BasePopularRecipeResponse(result = listOf(
        PopularRecipeResponse(1, "recipe 1" , "", ""),
        PopularRecipeResponse(2, "recipe 2" , "", ""),
)))

val error401Response = NetworkResponse.Error(401, "Error response body")
