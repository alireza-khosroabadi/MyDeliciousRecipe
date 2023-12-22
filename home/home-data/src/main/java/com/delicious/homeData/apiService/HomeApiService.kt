package com.delicious.homeData.apiService

import com.delicious.base.response.NetworkResponse
import com.delicious.homeData.model.popularRecipe.BaseRecipeRandomResponse
import retrofit2.http.GET

interface HomeApiService {

    @GET("/recipes/complexSearch?number=8")
    suspend fun RandomRecpie(): NetworkResponse<BaseRecipeRandomResponse>
}