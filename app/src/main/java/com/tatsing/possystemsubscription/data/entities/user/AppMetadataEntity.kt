package com.tatsing.possystemsubscription.data.entities.user

import com.tatsing.possystemsubscription.domain.model.login.AppMetadata

data class AppMetadataEntity(
    val provider: String,
    val providers: List<String>
)

fun AppMetadataEntity.toAppMetadata(): AppMetadata {
    return AppMetadata(
        provider = provider,
        providers = providers
    )
}