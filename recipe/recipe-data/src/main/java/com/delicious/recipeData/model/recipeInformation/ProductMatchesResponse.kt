package com.delicious.recipeData.model.recipeInformation

import com.delicious.base.model.data.baseDataModel.DataModel
import com.delicious.recipeDomain.model.recipeInformation.ProductMatches
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductMatchesResponse(
    @SerialName("id"            ) val id            : Int?    = null,
    @SerialName("title"         ) val title         : String? = null,
    @SerialName("description"   ) val description   : String? = null,
    @SerialName("price"         ) val price         : String? = null,
    @SerialName("imageUrl"      ) val imageUrl      : String? = null,
    @SerialName("averageRating" ) val averageRating : Double? = null,
    @SerialName("ratingCount"   ) val ratingCount   : Int?    = null,
    @SerialName("score"         ) val score         : Double? = null,
    @SerialName("link"          ) val link          : String? = null
):DataModel{
    override fun toDomainModel(): ProductMatches {
        TODO("Not yet implemented")
    }

}
