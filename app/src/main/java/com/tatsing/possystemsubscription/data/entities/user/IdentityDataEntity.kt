package com.tatsing.possystemsubscription.data.entities.user

import com.tatsing.possystemsubscription.domain.model.login.IdentityData

data class IdentityDataEntity(
    val email: String,
    val emailVerified: Boolean,
    val phoneVerified: Boolean,
    val sub: String
)

fun IdentityDataEntity.toIdentityData(): IdentityData {
    return IdentityData(
        email = email,
        emailVerified = emailVerified,
        phoneVerified = phoneVerified,
        sub = sub
    )
}