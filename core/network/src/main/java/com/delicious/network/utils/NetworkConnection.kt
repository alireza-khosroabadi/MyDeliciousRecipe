package com.delicious.network.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

internal class NetworkConnection @Inject constructor(@ApplicationContext private val context: Context) {
    fun isInternetOn(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return postAndroidMInternetCheck(connectivityManager)

    }

    private fun postAndroidMInternetCheck(
        connectivityManager: ConnectivityManager
    ): Boolean {
        val network = connectivityManager.activeNetwork
        val connection =
            connectivityManager.getNetworkCapabilities(network)
        return connection != null && (
                connection.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                        connection.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR))
    }

}