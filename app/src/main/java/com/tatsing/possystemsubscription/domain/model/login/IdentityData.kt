package com.tatsing.possystemsubscription.domain.model.login


import com.google.gson.annotations.SerializedName
import com.tatsing.possystemsubscription.data.entities.user.IdentityDataEntity

data class IdentityData(
    @SerializedName("email")
    val email: String,
    @SerializedName("email_verified")
    val emailVerified: Boolean,
    @SerializedName("phone_verified")
    val phoneVerified: Boolean,
    @SerializedName("sub")
    val sub: String
)

fun IdentityData.toEntity(): IdentityDataEntity {
    return IdentityDataEntity(
        email = email,
        emailVerified = emailVerified,
        phoneVerified = phoneVerified,
        sub = sub
    )
}