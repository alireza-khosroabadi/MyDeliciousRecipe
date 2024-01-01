package com.delicious.base.model.data.metrics

import com.delicious.base.model.domain.metrics.Metric
import kotlinx.serialization.SerialName

data class MetricResponse (

    @SerialName("amount"    ) val amount    : Int?    = null,
    @SerialName("unitLong"  ) val unitLong  : String? = null,
    @SerialName("unitShort" ) val unitShort : String? = null

)

fun MetricResponse.toDomainModel(): Metric = Metric(
    amount = amount?:0,
    unitLong = unitLong.orEmpty(),
    unitShort = unitShort.orEmpty()
)
