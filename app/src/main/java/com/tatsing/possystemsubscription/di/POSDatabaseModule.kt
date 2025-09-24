package com.tatsing.possystemsubscription.di

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tatsing.possystemsubscription.coverter.IdentityConverter
import com.tatsing.possystemsubscription.coverter.AppMetaDataConverter
import com.tatsing.possystemsubscription.coverter.MetaDataConverter
import com.tatsing.possystemsubscription.data.dao.UserDao
import com.tatsing.possystemsubscription.data.entities.user.UserEntity

@Database(
    entities = [
        UserEntity::class,
    ],
    version = 1,
    exportSchema = false
)

@TypeConverters(
    AppMetaDataConverter::class,
    IdentityConverter::class,
    MetaDataConverter::class,
)

abstract class POSDatabaseModule : RoomDatabase() {
    abstract fun userDao(): UserDao
}