package com.tatsing.possystemsubscription.domain.repository.local.user

import com.tatsing.possystemsubscription.data.entities.user.UserEntity

interface UserRepository {
    suspend fun insertUser(user: UserEntity)
    suspend fun getUserById(): String
    suspend fun getUsers(): UserEntity
    suspend fun deleteUser(user: UserEntity)
}