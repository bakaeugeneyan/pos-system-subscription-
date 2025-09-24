package com.tatsing.possystemsubscription.domain.repository.remote.login

import com.tatsing.possystemsubscription.base.NetworkResult
import com.tatsing.possystemsubscription.domain.model.login.SignInResponse
import kotlinx.coroutines.flow.Flow

interface SignInRepository {
    suspend fun signInWithEmail(
        email: String,
        password: String,
    ): Flow<NetworkResult<SignInResponse>>
}