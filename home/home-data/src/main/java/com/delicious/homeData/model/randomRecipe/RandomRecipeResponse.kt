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
    @SerialName("id") val id: Int? = null,
    @SerialName("title") val title: String? = null,
    @SerialName("readyInMinutes") val readyInMinutes: Int? = null,
    @SerialName("image") val image: String? = null,
    @SerialName("healthScore") val healthScore: Int? = null
)


fun RandomRecipeResponse.toDomainModel(): RandomRecipe = RandomRecipe(
    id?:0, title.orEmpty(), readyInMinutes?:0, image.orEmpty(), healthScore?:0
)
