package com.delicious.utility.network

import kotlinx.coroutines.flow.Flow

/**
 * Utility for reporting app connectivity status
 */
interface NetworkMonitor {
    val isOnline: Flow<Boolean>
}
