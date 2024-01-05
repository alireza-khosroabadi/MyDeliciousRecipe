package com.delicious.homeData.repository.homeRepository

import com.delicious.base.domain.ResultState
import com.delicious.base.response.NetworkResponse
import com.delicious.datastore.dataSource.FavoriteRecipeDataSource
import com.delicious.homeData.R
import com.delicious.homeData.apiService.HomeApiService
import com.delicious.homeData.mapper.favoriteRecipe.toFavoriteRecipePreferences
import com.delicious.homeData.model.popularRecipe.toDomainModel
import com.delicious.homeData.model.randomRecipe.toDomainModel
import com.delicious.homeDomain.model.mealType.MealType
import com.delicious.homeDomain.model.popularRecipe.PopularRecipe
import com.delicious.homeDomain.model.randomRecipe.RandomRecipe
import com.delicious.homeDomain.repository.homeRepository.HomeRepository
import javax.inject.Inject
import com.delicious.ui.R as uiR

class DefaultHomeRepository
    @Inject
    constructor(
        private val apiService: HomeApiService,
        private val favoriteRecipeDataSource: FavoriteRecipeDataSource,
    ) : HomeRepository {
        override suspend fun getPopularRecipe(): ResultState<List<PopularRecipe>> {
            return try {
                when (val response = apiService.popularRecipes()) {
                    is NetworkResponse.Error -> ResultState.Failure(response.code, response.message)
                    is NetworkResponse.Exception -> ResultState.Exception(response.throwable)
                    is NetworkResponse.Success ->
                        ResultState.Success(
                            response.data?.result
                                ?.map {
                                    it.toDomainModel()
                                }.orEmpty(),
                        )
                }
            } catch (e: Exception) {
                ResultState.Exception(e)
            }
        }

        override suspend fun getMealType(): ResultState<List<MealType>> =
            ResultState.Success(
                listOf(
                    MealType(R.string.meal_type_main_dishes, uiR.drawable.ic_maindish),
                    MealType(R.string.meal_type_salad, uiR.drawable.ic_carrots),
                    MealType(R.string.meal_type_fast_food, uiR.drawable.ic_fast_food),
                    MealType(R.string.meal_type_soup, uiR.drawable.ic_soup),
                    MealType(R.string.meal_type_dessert, uiR.drawable.ic_cake),
                    MealType(R.string.meal_type_beverage, uiR.drawable.ic_beverage),
                ),
            )

        override suspend fun getRandomRecipe(): ResultState<List<RandomRecipe>> {
            return try {
                when (val response = apiService.randomRecipes()) {
                    is NetworkResponse.Error -> ResultState.Failure(response.code, response.message)
                    is NetworkResponse.Exception -> ResultState.Exception(response.throwable)
                    is NetworkResponse.Success ->
                        ResultState.Success(
                            response.data?.recipes
                                ?.map {
                                    it.toDomainModel()
                                }.orEmpty(),
                        )
                }
            } catch (e: Exception) {
                e.printStackTrace()
                ResultState.Exception(e)
            }
        }

        override suspend fun updateFavoriteRecipe(recipe: RandomRecipe) {
            favoriteRecipeDataSource.updateFavoriteRecipes(recipe.toFavoriteRecipePreferences())
        }
    }
