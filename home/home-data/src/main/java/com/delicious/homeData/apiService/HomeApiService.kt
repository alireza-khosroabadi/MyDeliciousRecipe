package com.delicious.homeData.apiService

import com.delicious.base.response.BaseResponse
import com.delicious.homeData.model.randomRecipe.RandomRecipeResponse
import retrofit2.Response
import retrofit2.http.GET

interface HomeApiService {

    @GET("/recipes/complexSearch?number=8")
    suspend fun RandomRecpie(): Response<BaseResponse<List<RandomRecipeResponse>>>
}