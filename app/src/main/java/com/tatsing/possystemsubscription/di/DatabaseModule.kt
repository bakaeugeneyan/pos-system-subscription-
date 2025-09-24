package com.tatsing.possystemsubscription.di

import android.content.Context
import androidx.room.Room
import com.tatsing.possystemsubscription.data.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): POSDatabaseModule {
        return Room.databaseBuilder(
            context,
            POSDatabaseModule::class.java,
            "pos_system_subscription_database"
        ).build()
    }

    @Provides
    fun provideUserDao(appDatabase: POSDatabaseModule): UserDao {
        return appDatabase.userDao()
    }
}