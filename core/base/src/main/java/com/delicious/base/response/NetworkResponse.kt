package com.delicious.base.response

sealed class NetworkResponse <out T> {
    data class Success<out T>(val data: T?) : NetworkResponse<T>()
    data class Error(val code: Int, val message: String) : NetworkResponse<Nothing>()
    data class Exception(val throwable: Throwable) : NetworkResponse<Nothing>()
}
