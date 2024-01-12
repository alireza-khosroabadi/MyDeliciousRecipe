package com.delicious.datastore.dataSource

import androidx.datastore.core.DataStore
import com.delicious.datastore.model.favoriteRecipe.FavoriteRecipeListPreferences
import com.delicious.datastore.model.favoriteRecipe.FavoriteRecipePreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class FavoriteRecipeDataSource
    @Inject
    constructor(private val dataStore: DataStore<FavoriteRecipeListPreferences>) {
        suspend fun updateFavoriteRecipes(recipePreferences: FavoriteRecipePreferences) {
            dataStore.updateData {
                if (it.favoriteRecipeListList.any { it.id == recipePreferences.id }) {
                    FavoriteRecipeListPreferences.newBuilder(it)
                        .removeFavoriteRecipeList(it.favoriteRecipeListList.indexOf(recipePreferences))
                        .build()
                } else {
                    FavoriteRecipeListPreferences.newBuilder(it)
                        .addFavoriteRecipeList(recipePreferences)
                        .build()
                }
            }
        }

        fun favoriteRecipes(): Flow<List<FavoriteRecipePreferences>> =
            dataStore.data
                .catch { exception ->
                    if (exception is IOException) {
                        emit(
                            FavoriteRecipeListPreferences
                                .getDefaultInstance(),
                        )
                    } else {
                        throw exception
                    }
                }
                .map { it.favoriteRecipeListList }

        suspend fun removeRecipe(recipe: FavoriteRecipePreferences) {
            dataStore.updateData {
                FavoriteRecipeListPreferences.newBuilder(it)
                    .removeFavoriteRecipeList(it.favoriteRecipeListList.indexOf(recipe))
                    .build()
            }
        }
    }
