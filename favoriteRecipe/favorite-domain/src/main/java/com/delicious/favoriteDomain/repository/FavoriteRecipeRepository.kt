package com.delicious.favoriteDomain.repository

import com.delicious.base.domain.ResultState
import com.delicious.favoriteDomain.model.FavoriteRecipe
import kotlinx.coroutines.flow.Flow

interface FavoriteRecipeRepository {
    fun favoriteRecipes(): Flow<ResultState<List<FavoriteRecipe>>>

    suspend fun deleteRecipe(recipe: FavoriteRecipe)
}
