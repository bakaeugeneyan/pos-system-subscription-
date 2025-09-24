package com.tatsing.possystemsubscription.domain.usecase

import com.tatsing.possystemsubscription.domain.repository.remote.login.SignInRepository
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val signInRepository: SignInRepository
) {

    data class Field(val email: String, val password: String)

    suspend fun signInWithEmail(field: Field) =
        signInRepository.signInWithEmail(field.email, field.password)
}