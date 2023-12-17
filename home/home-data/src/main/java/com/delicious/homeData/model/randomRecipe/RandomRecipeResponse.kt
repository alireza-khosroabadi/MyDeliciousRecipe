package com.delicious.homeData.model.randomRecipe

import com.delicious.homeDomain.model.randomRecipe.RandomRecipe
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class BaseRecipeRandomResponse(
    @SerialName("results"       ) val result       : List<RandomRecipeResponse>,
    @SerialName("offset"       ) var offset       : Int?               = null,
    @SerialName("number"       ) var number       : Int?               = null,
    @SerialName("totalResults" ) var totalResults : Int?               = null
)

@Serializable
data class RandomRecipeResponse(
    @SerialName("id"        ) var id        : Int?    = null,
    @SerialName("title"     ) var title     : String? = null,
    @SerialName("image"     ) var image     : String? = null,
    @SerialName("imageType" ) var imageType : String? = null
)


fun RandomRecipeResponse.toDomainModel():RandomRecipe =
    RandomRecipe(id = id?: 0,
        title = title.orEmpty(),
        image = image.orEmpty(),
        imageType = imageType.orEmpty())