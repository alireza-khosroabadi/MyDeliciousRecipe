package com.delicious.favoriteData.fakeResults

import com.delicious.datastore.model.favoriteRecipe.FavoriteRecipePreferences
import kotlinx.coroutines.flow.flow

val fakeFavoriteRecipesList =
    listOf<FavoriteRecipePreferences>(
        FavoriteRecipePreferences.getDefaultInstance(),
        FavoriteRecipePreferences.getDefaultInstance(),
    )

val fakeFavoriteRecipesListAsFlow =
    flow {
        emit(
            fakeFavoriteRecipesList,
        )
    }
