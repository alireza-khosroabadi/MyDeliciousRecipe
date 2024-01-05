package com.delicious.recipeData.apiService

import com.delicious.base.response.NetworkResponse
import com.delicious.recipeData.model.recipeInformation.RecipeInformationResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface RecipeApiService {

    @GET("/recipes/{id}/information")
    suspend fun gerRecipeInformation(@Path("id") recipeId: Int): NetworkResponse<RecipeInformationResponse>
}
