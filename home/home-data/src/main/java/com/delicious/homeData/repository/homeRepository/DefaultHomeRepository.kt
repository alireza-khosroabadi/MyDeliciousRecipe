package com.delicious.homeData.repository.homeRepository

import com.delicious.base.domain.ResultState
import com.delicious.base.response.NetworkResponse
import com.delicious.homeData.R
import com.delicious.ui.R as uiR
import com.delicious.homeData.apiService.HomeApiService
import com.delicious.homeData.model.popularRecipe.toDomainModel
import com.delicious.homeDomain.model.mealType.MealType
import com.delicious.homeDomain.model.popularRecipe.PopularRecipe
import com.delicious.homeDomain.repository.homeRepository.HomeRepository
import javax.inject.Inject

class DefaultHomeRepository @Inject constructor(private val apiService: HomeApiService):HomeRepository {
    override suspend fun getRandomRecipe(): ResultState<List<PopularRecipe>> {
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

    override suspend fun getMealType(): ResultState<List<MealType>> = ResultState.Success(
        listOf(
            MealType(R.string.meal_type_main_dishes, uiR.drawable.ic_maindish),
            MealType(R.string.meal_type_salad, uiR.drawable.ic_carrots),
            MealType(R.string.meal_type_fast_food, uiR.drawable.ic_fast_food),
            MealType(R.string.meal_type_soup, uiR.drawable.ic_soup),
            MealType(R.string.meal_type_dessert, uiR.drawable.ic_cake),
        )
    )
}