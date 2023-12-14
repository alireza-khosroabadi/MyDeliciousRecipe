package com.delicious.analytics.base

/**
 * Interface for logging analytics events. See
 * `LogAnalyticsHelper` for implementations.
 */
interface AnalyticsHelper {
    fun logEvent(event: AnalyticsEvent)
}
