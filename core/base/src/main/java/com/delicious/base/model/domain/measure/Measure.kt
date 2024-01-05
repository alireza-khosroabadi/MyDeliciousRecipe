package com.delicious.base.model.domain.measure

import com.delicious.base.model.domain.metrics.Metric
import com.delicious.base.model.domain.metrics.Us

data class Measure(
    val metric : Metric,
    val us     : Us
)
