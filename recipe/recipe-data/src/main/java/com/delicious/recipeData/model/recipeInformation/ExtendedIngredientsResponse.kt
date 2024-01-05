package com.delicious.recipeData.model.recipeInformation

import com.delicious.base.model.data.baseDataModel.DataModel
import com.delicious.base.model.data.measure.MeasureResponse
import com.delicious.recipeDomain.model.recipeInformation.ExtendedIngredients
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ExtendedIngredientsResponse(
    @SerialName("aisle"        ) val aisle        : String?           = null,
    @SerialName("amount"       ) val amount       : Int?              = null,
    @SerialName("consitency"   ) val consitency   : String?           = null,
    @SerialName("id"           ) val id           : Int?              = null,
    @SerialName("image"        ) val image        : String?           = null,
    @SerialName("measures"     ) val measures     : MeasureResponse?  = MeasureResponse(),
    @SerialName("meta"         ) val meta         : ArrayList<String> = arrayListOf(),
    @SerialName("name"         ) val name         : String?           = null,
    @SerialName("original"     ) val original     : String?           = null,
    @SerialName("originalName" ) val originalName : String?           = null,
    @SerialName("unit"         ) val unit         : String?           = null
):DataModel{
    override fun toDomainModel():ExtendedIngredients = ExtendedIngredients(
        aisle = aisle.orEmpty(),
        amount = amount?:0,
        consitency= consitency.orEmpty(),
        id = id?:0,
        image = image.orEmpty(),
        measures= measures?.toDomainModel()?:MeasureResponse().toDomainModel(),
        meta= meta,
        name = name.orEmpty(),
        original= original.orEmpty(),
        originalName = originalName.orEmpty(),
        unit= unit.orEmpty()
    )
}
