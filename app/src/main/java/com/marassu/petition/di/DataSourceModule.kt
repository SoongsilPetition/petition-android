package com.marassu.petition.di

import com.marassu.data.source.UserRemoteDataSource
import com.marassu.domain.datasource.UserDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    @Singleton
    @Provides
    fun provideRemoteUserDataSource(userRemoteDataSource: UserRemoteDataSource): UserDataSource {
        return userRemoteDataSource
    }
}