package com.tatsing.possystemsubscription.domain.model.login


import com.google.gson.annotations.SerializedName
import com.tatsing.possystemsubscription.data.entities.user.AppMetadataEntity
import com.tatsing.possystemsubscription.data.entities.user.UserMetadataEntity

data class UserMetadata(
    @SerializedName("email_verified")
    val emailVerified: Boolean
)

fun UserMetadata.toEntity(): UserMetadataEntity {
    return UserMetadataEntity(
        emailVerified = emailVerified
    )
}