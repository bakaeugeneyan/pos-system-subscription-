package com.tatsing.possystemsubscription.domain.model.login


import com.google.gson.annotations.SerializedName
import com.tatsing.possystemsubscription.data.entities.user.AppMetadataEntity

data class AppMetadata(
    @SerializedName("provider")
    val provider: String,
    @SerializedName("providers")
    val providers: List<String>
)

fun AppMetadata.toEntity(): AppMetadataEntity {
    return AppMetadataEntity(
        provider = provider,
        providers = providers
    )
}