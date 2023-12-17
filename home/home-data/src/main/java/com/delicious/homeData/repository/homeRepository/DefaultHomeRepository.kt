package com.delicious.homeData.repository.homeRepository

import com.delicious.base.domain.ResultState
import com.delicious.homeData.apiService.HomeApiService
import com.delicious.homeData.model.randomRecipe.toDomainModel
import com.delicious.homeDomain.model.randomRecipe.RandomRecipe
import com.delicious.homeDomain.repository.homeRepository.HomeRepository
import javax.inject.Inject

class DefaultHomeRepository @Inject constructor(private val apiService: HomeApiService):HomeRepository {
    override suspend fun getRandomRecipe(): ResultState<List<RandomRecipe>> {
        return try {
            val response =  apiService.RandomRecpie()
            return if (response.isSuccessful) {
                val continents = response.body()?.result
                    ?.map {
                        it.toDomainModel()
                    }.orEmpty()
                ResultState.Success(continents)
            } else {
                ResultState.Failure(
                    response.errorBody().toString()
                        .ifEmpty { "Something went wrong" }
                )
            }
        } catch (exception: Exception) {
            ResultState.Exception(exception)
        }
    }
}