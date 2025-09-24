package com.tatsing.possystemsubscription.domain.model.login


import com.google.gson.annotations.SerializedName

data class UserMetadata(
    @SerializedName("email_verified")
    val emailVerified: Boolean
)