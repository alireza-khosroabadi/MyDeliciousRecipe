package com.delicious.homeData.model.randomRecipe

import com.delicious.homeDomain.model.randomRecipe.RandomRecipe
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BaseRandomRecipeResponse(
    @SerialName("recipes") val recipes: List<RandomRecipeResponse>
)

@Serializable
data class RandomRecipeResponse(
    @SerialName("id") val id: Int? = null,
    @SerialName("title") val title: String? = null,
    @SerialName("readyInMinutes") val readyInMinutes: Int? = null,
    @SerialName("image") val image: String? = null,
    @SerialName("sourceUrl") val sourceUrl: String? = null,
    @SerialName("healthScore") val healthScore: Int? = null
)


fun RandomRecipeResponse.toDomainModel(): RandomRecipe = RandomRecipe(
    id= id ?: 0,
   title =  title.orEmpty(),
    readyInMinutes = readyInMinutes ?: 0,
    image = image.orEmpty(),
    sourceUrl = sourceUrl.orEmpty(),
    healthScore = healthScore ?: 0
)
