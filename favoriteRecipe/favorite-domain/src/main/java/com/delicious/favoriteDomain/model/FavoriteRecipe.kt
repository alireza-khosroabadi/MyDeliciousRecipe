package com.delicious.favoriteDomain.model

data class FavoriteRecipe(
    val id: Int,
    val title: String,
    val readyInMinutes: Int,
    val image: String,
    val healthScore: Int,
    val sourceUrl: String,
)
