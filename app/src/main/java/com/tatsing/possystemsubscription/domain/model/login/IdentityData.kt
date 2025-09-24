package com.tatsing.possystemsubscription.domain.model.login


import com.google.gson.annotations.SerializedName

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