package com.tatsing.possystemsubscription.data.services

import com.tatsing.possystemsubscription.data.dto.SignInRequest
import com.tatsing.possystemsubscription.domain.model.login.SignInResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface SignInService {

    @POST("auth/v1/token?grant_type=password")
    suspend fun signInWithEmail(
        @Body request: SignInRequest
    ): Response<SignInResponse>
}