package com.tatsing.possystemsubscription.domain.repository.local.user

import com.tatsing.possystemsubscription.data.dao.UserDao
import com.tatsing.possystemsubscription.data.entities.user.UserEntity
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao
): UserRepository {
    override suspend fun insertUser(user: UserEntity) {
        return userDao.insertUser(user)
    }

    override suspend fun getUserById(): String {
        return userDao.getUserById()
    }

    override suspend fun getUsers(): UserEntity {
        return userDao.getUsers()
    }

    override suspend fun deleteUser(user: UserEntity) {
        userDao.deleteUser(user)
    }
}