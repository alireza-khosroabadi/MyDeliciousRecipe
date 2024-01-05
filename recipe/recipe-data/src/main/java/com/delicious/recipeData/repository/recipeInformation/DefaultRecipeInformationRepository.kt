package com.delicious.recipeData.repository.recipeInformation

import com.delicious.base.domain.ResultState
import com.delicious.base.response.NetworkResponse
import com.delicious.recipeData.apiService.RecipeApiService
import com.delicious.recipeDomain.model.recipeInformation.RecipeInformation
import com.delicious.recipeDomain.repository.RecipeInformationRepository
import javax.inject.Inject

class DefaultRecipeInformationRepository @Inject constructor(private val apiService: RecipeApiService) :
    RecipeInformationRepository {

    override suspend fun recipeInformation(recipeId: Int): ResultState<RecipeInformation> {
        return try {
            when (val response = apiService.gerRecipeInformation(recipeId)) {
                is NetworkResponse.Error -> ResultState.Failure(response.code, response.message)
                is NetworkResponse.Exception -> ResultState.Exception(response.throwable)
                is NetworkResponse.Success -> response.data?.let {
                    ResultState.Success(
                        it.toDomainModel()
                    )
                } ?: ResultState.Failure(-1, "Recipe Not Found")
            }
        } catch (e: Exception) {
            ResultState.Exception(e)
        }
    }
}
