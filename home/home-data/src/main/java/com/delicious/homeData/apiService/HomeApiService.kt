package com.delicious.homeData.apiService

import com.delicious.base.response.NetworkResponse
import com.delicious.homeData.model.popularRecipe.BasePopularRecipeResponse
import com.delicious.homeData.model.randomRecipe.BaseRandomRecipeResponse
import retrofit2.http.GET

interface HomeApiService {

    @GET("/recipes/complexSearch?number=8")
    suspend fun popularRecipes(): NetworkResponse<BasePopularRecipeResponse>

    @GET("/recipes/random?number=5")
    suspend fun randomRecipes(): NetworkResponse<BaseRandomRecipeResponse>
}
