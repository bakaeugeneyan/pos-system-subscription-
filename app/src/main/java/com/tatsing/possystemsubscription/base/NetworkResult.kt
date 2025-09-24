package com.tatsing.possystemsubscription.base

sealed class NetworkResult<T>(
    val data: T? = null,
    val message: String? = null,
    val responseCode: String? = null
) {
    
    class Loading<T> : NetworkResult<T>()
    
    class Success<T>(data: T) : NetworkResult<T>(data)

    class Error<T>(message: String, data: T? = null) : NetworkResult<T>(data, message)

//    class Nothing<T> : NetworkResult<T>()

}