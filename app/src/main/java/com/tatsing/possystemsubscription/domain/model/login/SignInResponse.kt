package com.tatsing.possystemsubscription.domain.model.login


import com.google.gson.annotations.SerializedName

data class SignInResponse(
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("expires_at")
    val expiresAt: Int,
    @SerializedName("expires_in")
    val expiresIn: Int,
    @SerializedName("refresh_token")
    val refreshToken: String,
    @SerializedName("token_type")
    val tokenType: String,
    @SerializedName("user")
    val user: User,
    @SerializedName("weak_password")
    val weakPassword: Any
)