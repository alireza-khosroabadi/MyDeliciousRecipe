package com.delicious.homeData.model.popularRecipe

import com.delicious.homeDomain.model.popularRecipe.PopularRecipe
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class BasePopularRecipeResponse(
    @SerialName("results"       ) val result       : List<PopularRecipeResponse>,
    @SerialName("offset"       ) var offset       : Int?               = null,
    @SerialName("number"       ) var number       : Int?               = null,
    @SerialName("totalResults" ) var totalResults : Int?               = null
)

@Serializable
data class PopularRecipeResponse(
    @SerialName("id"        ) var id        : Int?    = null,
    @SerialName("title"     ) var title     : String? = null,
    @SerialName("image"     ) var image     : String? = null,
    @SerialName("imageType" ) var imageType : String? = null
)


fun PopularRecipeResponse.toDomainModel():PopularRecipe =
    PopularRecipe(id = id?: 0,
        title = title.orEmpty(),
        image = image.orEmpty(),
        imageType = imageType.orEmpty())