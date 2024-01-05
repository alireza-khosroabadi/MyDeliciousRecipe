package com.delicious.analytics.extention

import com.delicious.analytics.base.AnalyticsEvent
import com.delicious.analytics.base.AnalyticsHelper

fun AnalyticsHelper.logScreenView(screenName: String) {
    logEvent(
        AnalyticsEvent(
            type = AnalyticsEvent.Types.SCREEN_VIEW,
            extras = listOf(
                AnalyticsEvent.Param(AnalyticsEvent.ParamKeys.SCREEN_NAME, screenName),
            ),
        ),
    )
}
