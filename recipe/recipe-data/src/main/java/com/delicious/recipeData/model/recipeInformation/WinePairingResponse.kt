package com.delicious.recipeData.model.recipeInformation

import com.delicious.base.model.data.baseDataModel.DataModel
import com.delicious.recipeDomain.model.recipeInformation.WinePairing
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WinePairingResponse(
    @SerialName("pairedWines"    ) val pairedWines    : ArrayList<String>         = arrayListOf(),
    @SerialName("pairingText"    ) val pairingText    : String?                   = null,
    @SerialName("productMatches" ) val productMatches : ArrayList<ProductMatchesResponse> = arrayListOf()
):DataModel {
    override fun toDomainModel(): WinePairing {
        TODO("Not yet implemented")
    }
}
