package com.marassu.petition.di

import com.marassu.data.repositoryImpl.UserRepositoryImpl
import com.marassu.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Singleton
    @Provides
    fun providesUserRepository(repository: UserRepositoryImpl): UserRepository {
        return repository
    }
}