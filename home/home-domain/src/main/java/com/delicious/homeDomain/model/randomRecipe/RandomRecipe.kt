package com.delicious.homeDomain.model.randomRecipe

data class RandomRecipe(
    val id: Int,
    val title: String,
    val readyInMinutes: Int,
    val image: String,
    val healthScore: Int,
    val sourceUrl: String,
    var isFavorite:Boolean = false
)
