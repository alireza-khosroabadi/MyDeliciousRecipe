package com.delicious.favoriteData.repository

import com.delicious.base.domain.ResultState
import com.delicious.datastore.dataSource.FavoriteRecipeDataSource
import com.delicious.datastore.model.favoriteRecipe.FavoriteRecipePreferences
import com.delicious.favoriteData.mapper.toDomainModel
import com.delicious.favoriteData.mapper.toPreferenceModel
import com.delicious.favoriteDomain.model.FavoriteRecipe
import com.delicious.favoriteDomain.repository.FavoriteRecipeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DefaultFavoriteRecipeRepository
    @Inject
    constructor(private val favoriteRecipeDataSource: FavoriteRecipeDataSource) :
    FavoriteRecipeRepository {
        override fun favoriteRecipes(): Flow<ResultState<List<FavoriteRecipe>>> =
            favoriteRecipeDataSource.favoriteRecipes()
                .map<List<FavoriteRecipePreferences>, ResultState<List<FavoriteRecipe>>> { data ->
                    ResultState.Success(
                        data.map { it.toDomainModel() },
                    )
                }
                .catch { exception -> emit(ResultState.Exception(exception)) }

        override suspend fun deleteRecipe(recipe: FavoriteRecipe) {
            favoriteRecipeDataSource.removeRecipe(recipe.toPreferenceModel())
        }
    }
