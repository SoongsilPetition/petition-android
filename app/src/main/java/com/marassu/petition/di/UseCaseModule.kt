package com.marassu.petition.di

import com.marassu.domain.repository.UserRepository
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
}