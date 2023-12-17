package com.delicious.homeData.repository.homeRepository

import com.delicious.base.domain.ResultState
import com.delicious.base.response.NetworkResponse
import com.delicious.homeData.apiService.HomeApiService
import com.delicious.homeData.model.randomRecipe.toDomainModel
import com.delicious.homeDomain.model.randomRecipe.RandomRecipe
import com.delicious.homeDomain.repository.homeRepository.HomeRepository
import javax.inject.Inject

class DefaultHomeRepository @Inject constructor(private val apiService: HomeApiService):HomeRepository {
    override suspend fun getRandomRecipe(): ResultState<List<RandomRecipe>> {
        return try {
            when (val response = apiService.RandomRecpie()) {
                is NetworkResponse.Error -> ResultState.Failure(response.code, response.message)
                is NetworkResponse.Exception -> ResultState.Exception(response.throwable)
                is NetworkResponse.Success -> ResultState.Success(
                    response.data?.result
                        ?.map {
                            it.toDomainModel()
                        }.orEmpty()
                )
            }
        }catch(e: Exception) {
            ResultState.Exception(e)
        }
    }
}