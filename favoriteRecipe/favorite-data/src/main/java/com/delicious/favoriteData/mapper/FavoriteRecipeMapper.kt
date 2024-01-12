package com.delicious.favoriteData.mapper

import com.delicious.datastore.model.favoriteRecipe.FavoriteRecipePreferences
import com.delicious.favoriteDomain.model.FavoriteRecipe

fun FavoriteRecipePreferences.toDomainModel(): FavoriteRecipe =
    FavoriteRecipe(
        id = id,
        title = title,
        readyInMinutes = readyInMinutes,
        image = image,
        healthScore = healthScore,
        sourceUrl = sourceUrl,
    )

fun FavoriteRecipe.toPreferenceModel(): FavoriteRecipePreferences =
    FavoriteRecipePreferences.newBuilder()
        .setId(id)
        .setReadyInMinutes(readyInMinutes)
        .setTitle(title)
        .setImage(image)
        .setHealthScore(healthScore)
        .setSourceUrl(sourceUrl)
        .build()
