package com.tatsing.possystemsubscription.domain.repository

import com.tatsing.possystemsubscription.domain.repository.remote.login.SignInRepository
import com.tatsing.possystemsubscription.domain.repository.remote.login.SignInRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindSignInRepository(source: SignInRepositoryImpl): SignInRepository
}