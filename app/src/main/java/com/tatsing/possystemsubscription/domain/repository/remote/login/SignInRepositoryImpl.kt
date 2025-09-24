package com.tatsing.possystemsubscription.domain.repository.remote.login

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.tatsing.possystemsubscription.base.BaseApiResponse
import com.tatsing.possystemsubscription.base.NetworkResult
import com.tatsing.possystemsubscription.data.source.remote.signin.SignInRemoteSource
import com.tatsing.possystemsubscription.domain.model.login.SignInResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SignInRepositoryImpl @Inject constructor(
    private val signInRemoteSource: SignInRemoteSource,
): BaseApiResponse(), SignInRepository {

    override suspend fun signInWithEmail(
        email: String,
        password: String
    ): Flow<NetworkResult<SignInResponse>> {
        return flow<NetworkResult<SignInResponse>> {
            emit(NetworkResult.Loading())

            try {
                val response = signInRemoteSource.signInWithEmail(email, password)
                if (response.isSuccessful) {
                    emit(NetworkResult.Success(response.body()!!))
                }else {
                    val errorMessage = response.errorBody()?.let { errorBody ->
                        val errorJson =
                            Gson().fromJson(errorBody.charStream(), JsonObject::class.java)
                        errorJson["msg"]?.asString ?: "Unknown error"
                    } ?: "Unknown error"

                    emit(NetworkResult.Error(errorMessage))
                }

            } catch (e: Exception) {
                emit(NetworkResult.Error("Network error: ${e.localizedMessage}"))
            }
        }.flowOn(Dispatchers.IO)
    }
}