package com.tatsing.possystemsubscription.data.source.remote.signin

import com.tatsing.possystemsubscription.data.dto.SignInRequest
import com.tatsing.possystemsubscription.domain.model.login.SignInResponse
import com.tatsing.possystemsubscription.data.services.SignInService
import retrofit2.Response
import javax.inject.Inject

class SignInRemoteSourceImpl @Inject constructor(
    private val signInService: SignInService
) : SignInRemoteSource {
    override suspend fun signInWithEmail(email: String, password: String): Response<SignInResponse> {
        val signInRequest = SignInRequest(email = email, password = password)
        return signInService.signInWithEmail(signInRequest)
    }
}