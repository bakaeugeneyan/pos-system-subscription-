package com.tatsing.possystemsubscription.data.entities.user

import com.tatsing.possystemsubscription.domain.model.login.Identity

data class IdentityEntity(
    val createdAt: String,
    val email: String,
    val id: String,
    val identityDataEntity: IdentityDataEntity,
    val identityId: String,
    val lastSignInAt: String,
    val provider: String,
    val updatedAt: String,
    val userId: String
)

fun IdentityEntity.toIdentity(): Identity {
    return Identity(
        createdAt = createdAt,
        email = email,
        id = id,
        identityData = identityDataEntity.toIdentityData(),
        identityId = identityId,
        lastSignInAt = lastSignInAt,
        provider = provider,
        updatedAt = updatedAt,
        userId = userId
    )
}
