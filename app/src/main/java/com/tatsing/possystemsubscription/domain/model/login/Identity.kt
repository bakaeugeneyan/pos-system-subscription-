package com.tatsing.possystemsubscription.domain.model.login


import com.google.gson.annotations.SerializedName
import com.tatsing.possystemsubscription.data.entities.user.IdentityEntity

data class Identity(
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("identity_data")
    val identityData: IdentityData,
    @SerializedName("identity_id")
    val identityId: String,
    @SerializedName("last_sign_in_at")
    val lastSignInAt: String,
    @SerializedName("provider")
    val provider: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("user_id")
    val userId: String
)

fun Identity.toEntity(): IdentityEntity {
    return IdentityEntity(
        createdAt = createdAt,
        email = email,
        id = id,
        identityDataEntity = identityData.toEntity(),
        identityId = identityId,
        lastSignInAt = lastSignInAt,
        provider = provider,
        updatedAt = updatedAt,
        userId = userId
    )
}
