package com.marassu.petition.di

import com.marassu.data.repositoryImpl.CategoryRepositoryImpl
import com.marassu.data.repositoryImpl.ConcurRepositoryImpl
import com.marassu.data.repositoryImpl.PetitionAnswerRepositoryImpl
import com.marassu.data.repositoryImpl.PetitionRepositoryImpl
import com.marassu.data.repositoryImpl.UserRepositoryImpl
import com.marassu.domain.repository.CategoryRepository
import com.marassu.domain.repository.ConcurRepository
import com.marassu.domain.repository.PetitionAnswerRepository
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
    fun providesPetitionRepository(repository: PetitionRepositoryImpl): PetitionRepository {
        return repository
    }

    @Singleton
    @Provides
    fun providesConcurRepository(repository: ConcurRepositoryImpl): ConcurRepository {
        return repository
    }

    @Singleton
    @Provides
    fun providePetitionAnswerRepository(repository: PetitionAnswerRepositoryImpl): PetitionAnswerRepository {
        return repository
    }

    @Singleton
    @Provides
    fun provideCategoryRepository(repository: CategoryRepositoryImpl): CategoryRepository {
        return repository
    }
}