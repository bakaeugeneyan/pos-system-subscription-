package com.tatsing.possystemsubscription.data.entities.user

import com.tatsing.possystemsubscription.domain.model.login.UserMetadata

data class UserMetadataEntity(
    val emailVerified: Boolean
)

fun UserMetadataEntity.toUserMetadata(): UserMetadata {
    return UserMetadata(
        emailVerified = emailVerified
    )
}