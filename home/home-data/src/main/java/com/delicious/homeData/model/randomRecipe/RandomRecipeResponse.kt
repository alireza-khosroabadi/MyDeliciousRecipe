package com.delicious.homeData.model.randomRecipe

import com.delicious.homeDomain.model.randomRecipe.RandomRecipe
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BaseRandomRecipeResponse(
    @SerialName("recipes") val recipes:List<RandomRecipeResponse>
)
@Serializable
data class RandomRecipeResponse(
    @SerialName("id") val id: Int,
    @SerialName("title") val title: String,
    @SerialName("readyInMinutes") val readyInMinutes: Int,
    @SerialName("image") val image: String?,
    @SerialName("healthScore") val healthScore: Int
)


fun RandomRecipeResponse.toDomainModel(): RandomRecipe = RandomRecipe(
    id, title, readyInMinutes, image.orEmpty(), healthScore
)