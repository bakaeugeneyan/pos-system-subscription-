package com.tatsing.possystemsubscription.data.entities.user

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tatsing.possystemsubscription.domain.model.login.User

@Entity(tableName = UserEntity.TABLE_NAME)
data class UserEntity(
    val appMetadataEntity: AppMetadataEntity,
    val aud: String,
    val confirmedAt: String,
    val createdAt: String,
    val email: String,
    val emailConfirmedAt: String,
    @PrimaryKey
    val id: String,
    val identitiesEntity: List<IdentityEntity>,
    val isAnonymous: Boolean,
    val lastSignInAt: String,
    val phone: String,
    val role: String,
    val updatedAt: String,
    val userMetadataEntity: UserMetadataEntity,
    val password: String? = null
) {
    companion object {
        const val TABLE_NAME = "user"
    }
}

fun UserEntity.toUserResponse(): User {
    return User(
        appMetadata = appMetadataEntity.toAppMetadata(),
        aud = aud,
        confirmedAt = confirmedAt,
        createdAt = createdAt,
        email = email,
        emailConfirmedAt = emailConfirmedAt,
        id = id,
        identities = identitiesEntity.map { it.toIdentity() },
        isAnonymous = isAnonymous,
        lastSignInAt = lastSignInAt,
        phone = phone,
        role = role,
        updatedAt = updatedAt,
        userMetadata = userMetadataEntity.toUserMetadata(),
    )
}