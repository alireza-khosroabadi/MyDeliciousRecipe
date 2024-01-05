package com.delicious.homeData.mapper.favoriteRecipe

import com.delicious.datastore.model.favoriteRecipe.FavoriteRecipePreferences
import com.delicious.homeDomain.model.randomRecipe.RandomRecipe

fun RandomRecipe.toFavoriteRecipePreferences(): FavoriteRecipePreferences =
    FavoriteRecipePreferences
        .newBuilder()
        .setImage(image)
        .setId(id)
        .setTitle(title)
        .setHealthScore(healthScore)
        .setSourceUrl(sourceUrl)
        .setReadyInMinutes(readyInMinutes)
        .build()

fun FavoriteRecipePreferences.toRecipe(): RandomRecipe =
    RandomRecipe(
        this.id,
        title,
        readyInMinutes,
        image,
        healthScore,
        sourceUrl,
        true,
    )
