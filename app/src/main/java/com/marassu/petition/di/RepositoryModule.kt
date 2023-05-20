package com.marassu.petition.di

import com.marassu.data.repositoryImpl.PetitionRepositoryImpl
import com.marassu.data.repositoryImpl.UserRepositoryImpl
import com.marassu.domain.repository.PetitionRepository
import com.marassu.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun providesUserRepository(repository: UserRepositoryImpl): UserRepository {
        return repository
    }

    @Singleton
    @Provides
    fun providePetitionRepository(repository: PetitionRepositoryImpl): PetitionRepository {
        return repository
    }
}