package com.delicious.base.model.data.measure

import com.delicious.base.model.data.metrics.MetricResponse
import com.delicious.base.model.data.metrics.UsResponse
import com.delicious.base.model.data.metrics.toDomainModel
import com.delicious.base.model.domain.measure.Measure
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MeasureResponse(
    @SerialName("metric" ) val metric : MetricResponse? = MetricResponse(),
    @SerialName("us"     ) val us     : UsResponse?     = UsResponse()
){
    fun toDomainModel(): Measure = Measure(
        metric = metric?.toDomainModel()?:MetricResponse().toDomainModel(),
        us = us?.toDomainModel()?:UsResponse().toDomainModel()
    )
}
