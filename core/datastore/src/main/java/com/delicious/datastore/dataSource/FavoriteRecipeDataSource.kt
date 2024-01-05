package com.delicious.datastore.dataSource

import androidx.datastore.core.DataStore
import com.delicious.datastore.model.favoriteRecipe.FavoriteRecipeListPreferences
import com.delicious.datastore.model.favoriteRecipe.FavoriteRecipePreferences
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
    }
