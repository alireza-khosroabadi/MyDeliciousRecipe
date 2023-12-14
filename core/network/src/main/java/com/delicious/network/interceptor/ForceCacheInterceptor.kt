package com.delicious.network.interceptor

import com.delicious.network.utils.NetworkConnection
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

internal class ForceCacheInterceptor(private val networkConnection: NetworkConnection):Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder: Request.Builder = chain.request().newBuilder()
        if (!networkConnection.isInternetOn()) {
            builder.cacheControl(CacheControl.FORCE_CACHE)
        }
        return chain.proceed(builder.build())
    }
}