package com.tatsing.possystemsubscription.data.source.remote.signin

import com.tatsing.possystemsubscription.domain.model.login.SignInResponse
import retrofit2.Response

interface SignInRemoteSource {
    suspend fun signInWithEmail(
        email: String,
        password: String
    ):Response<SignInResponse>
}