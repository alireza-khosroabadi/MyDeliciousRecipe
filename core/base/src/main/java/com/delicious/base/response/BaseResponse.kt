package com.delicious.base.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse<DATA>(
    @SerialName("result"       ) val result       : DATA,
    @SerialName("offset"       ) var offset       : Int?               = null,
    @SerialName("number"       ) var number       : Int?               = null,
    @SerialName("totalResults" ) var totalResults : Int?               = null
) {
}