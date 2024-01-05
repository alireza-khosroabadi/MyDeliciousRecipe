package com.delicious.base.model.domain.metrics

import kotlinx.serialization.SerialName

data class Metric (
    val amount    : Int,
    val unitLong  : String,
    val unitShort : String

)
