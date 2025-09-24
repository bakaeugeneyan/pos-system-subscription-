package com.tatsing.possystemsubscription.data.source.remote

import com.tatsing.possystemsubscription.data.source.remote.signin.SignInRemoteSource
import com.tatsing.possystemsubscription.data.source.remote.signin.SignInRemoteSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class RemoteSourceModule {
    
    @Binds
    @Singleton
    abstract fun bindSignInRemoteSource(signInRemoteSourceImpl: SignInRemoteSourceImpl): SignInRemoteSource
}