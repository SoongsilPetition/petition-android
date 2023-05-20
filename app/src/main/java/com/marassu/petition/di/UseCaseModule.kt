package com.marassu.petition.di

import com.marassu.domain.repository.ConcurRepository
import com.marassu.domain.repository.PetitionRepository
import com.marassu.domain.repository.UserRepository
import com.marassu.domain.usecase.GetConcurListUseCase
import com.marassu.domain.usecase.GetPetitionListUseCase
import com.marassu.domain.usecase.GetUserConcurListUseCase
import com.marassu.domain.usecase.PostConcurUseCase
import com.marassu.domain.usecase.PostPetitionUseCase
import com.marassu.domain.usecase.PostUserLoginUseCase
import com.marassu.domain.usecase.PostUserRegisterUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {
    @Provides
    @Singleton
    fun providePostUserRegisterUseCase(repository: UserRepository): PostUserRegisterUseCase {
        return PostUserRegisterUseCase(repository)
    }

    @Provides
    @Singleton
    fun providePostUserLoginUseCase(repository: UserRepository): PostUserLoginUseCase {
        return PostUserLoginUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetPetitionListUseCase(repository: PetitionRepository): GetPetitionListUseCase {
        return GetPetitionListUseCase(repository)
    }

    @Provides
    @Singleton
    fun providePostPetitionUseCase(repository: PetitionRepository): PostPetitionUseCase {
        return PostPetitionUseCase(repository)
    }

    @Provides
    @Singleton
    fun providePostConcurUseCase(repository: ConcurRepository): PostConcurUseCase {
        return PostConcurUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetConcurListUseCase(repository: ConcurRepository): GetConcurListUseCase {
        return GetConcurListUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetUserConcurListUseCase(repository: ConcurRepository): GetUserConcurListUseCase {
        return GetUserConcurListUseCase(repository)
    }
}