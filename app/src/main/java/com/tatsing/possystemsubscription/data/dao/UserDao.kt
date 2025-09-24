package com.tatsing.possystemsubscription.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tatsing.possystemsubscription.data.entities.user.UserEntity

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity)

    @Query("SELECT id FROM ${UserEntity.TABLE_NAME} LIMIT 1")
    suspend fun getUserById(): String

    @Query("SELECT * FROM ${UserEntity.TABLE_NAME} LIMIT 1")
    suspend fun getUsers(): UserEntity

    @Delete
    suspend fun deleteUser(user: UserEntity)
}