package com.tatsing.possystemsubscription.base

import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response

abstract class BaseApiResponse {
    suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): NetworkResult<T> {
        try {
            val response = apiCall()
            if (response.isSuccessful) {
                val body = response.body()
                body?.let {
                    return NetworkResult.Success(it)
                }
            }
            
            var errorBody = response.errorBody()?.string().toString()
            var errorMsg: String? = null

            // Check if errorBody is not null and parse the JSON string to extract the message
            errorBody.let { jsonString ->
                try {
                    val jsonObject = JSONObject(jsonString)
                    errorMsg = jsonObject.getString("message")
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }
            return error(errorMessage = "$errorMsg")
        } catch (ex: Exception) {
            return error(errorMessage = ex.message ?: ex.toString())
        }
    }

    private fun <T> error(errorMessage: String): NetworkResult<T> =
        NetworkResult.Error(errorMessage)
}