package com.delicious.network.interceptor

import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response
import java.util.concurrent.TimeUnit

internal class HttpCacheInterceptor:Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(chain.request()).newBuilder().apply {
            val cacheControl = CacheControl.Builder()
                .maxAge(1, TimeUnit.DAYS)
                .build()
            header("Cache-Control", cacheControl.toString())
        }
            .build()
    }
}