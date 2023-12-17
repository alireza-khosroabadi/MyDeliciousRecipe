package com.delicious.network.callAdapter

import com.delicious.base.response.NetworkResponse
import okhttp3.Request
import okio.IOException
import okio.Timeout
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NetworkResponseCall<T>(private val delegate: Call<T>) : Call<NetworkResponse<T>> {
    override fun enqueue(callback: Callback<NetworkResponse<T>>) {
        return delegate.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                val body = response.body()

                if (response.isSuccessful) {
                        callback.onResponse(
                            this@NetworkResponseCall,
                            Response.success(NetworkResponse.Success(body))
                        )
                } else {
                        handleErrorResponse(response)
                }
            }

            override fun onFailure(call: Call<T>, throwable: Throwable) {
                val networkResponse = when (throwable) {
                    is IOException -> NetworkResponse.Exception(throwable)
                    else -> NetworkResponse.Exception(throwable)
                }
                callback.onResponse(this@NetworkResponseCall, Response.success(networkResponse))
            }
        })
    }


    private fun handleErrorResponse(response: Response<T>): Response<NetworkResponse<T>>? {
        val errorBody = response.errorBody()?.string() ?: ""
        return try {
            val errorJson = JSONObject(errorBody)
            val errorCode = errorJson.optInt("code", 500)
            val errorMessage = errorJson.optString("message", errorBody)
            Response.success(NetworkResponse.Error(errorCode, errorMessage))
        } catch (e: JSONException) {
            // Handle JSON parsing error
            Response.success(NetworkResponse.Error(500, "Error parsing JSON"))
        }
    }


    override fun isExecuted() = delegate.isExecuted

    override fun clone() = NetworkResponseCall(delegate.clone())

    override fun isCanceled() = delegate.isCanceled

    override fun cancel() = delegate.cancel()

    override fun execute(): Response<NetworkResponse<T>> {
        throw UnsupportedOperationException("NetworkResponseCall doesn't support execute")
    }

    override fun timeout(): Timeout = delegate.timeout()
    override fun request(): Request = delegate.request()

}