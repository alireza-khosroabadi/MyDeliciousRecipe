package com.delicious.network.interceptor

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class RequestHeaderInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain
            .request()
            .newBuilder()
            .header("token", "")
            .build()
        return chain.proceed(request)
    }
}