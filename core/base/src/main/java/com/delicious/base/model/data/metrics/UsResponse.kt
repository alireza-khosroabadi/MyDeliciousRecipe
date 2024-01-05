package com.delicious.base.model.data.metrics

import com.delicious.base.model.domain.metrics.Us
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UsResponse(
    @SerialName("amount"    ) val amount    : Int?    = null,
    @SerialName("unitLong"  ) val unitLong  : String? = null,
    @SerialName("unitShort" ) val unitShort : String? = null
)

fun UsResponse.toDomainModel(): Us = Us(
    amount = amount?:0,
    unitLong = unitLong.orEmpty(),
    unitShort = unitShort.orEmpty()
)
