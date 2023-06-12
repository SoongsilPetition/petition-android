package com.marassu.petition.di

import com.marassu.domain.repository.CategoryRepository
import com.marassu.domain.repository.ConcurRepository
import com.marassu.domain.repository.PetitionAnswerRepository
import com.marassu.domain.repository.PetitionRepository
import com.marassu.domain.repository.UserRepository
import com.marassu.domain.usecase.DeletePetitionAnswerUseCase
import com.marassu.domain.usecase.GetCategoryUseCase
import com.marassu.domain.usecase.GetCompletedPetitionListUseCase
import com.marassu.domain.usecase.GetConcurListUseCase
import com.marassu.domain.usecase.GetPetitionAnswerUseCase
import com.marassu.domain.usecase.GetPetitionListUseCase
import com.marassu.domain.usecase.GetPetitionUseCase
import com.marassu.domain.usecase.GetUserConcurListUseCase
import com.marassu.domain.usecase.PatchPetitionAnswerUseCase
import com.marassu.domain.usecase.PostConcurUseCase
import com.marassu.domain.usecase.PostPetitionAnswerUseCase
import com.marassu.domain.usecase.PostPetitionUseCase
import com.marassu.domain.usecase.PostUserLoginUseCase
import com.marassu.domain.usecase.PostUserRegisterUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    @Singleton
    fun providesPostUserRegisterUseCase(repository: UserRepository): PostUserRegisterUseCase {
        return PostUserRegisterUseCase(repository)
    }

    @Provides
    @Singleton
    fun providesPostUserLoginUseCase(repository: UserRepository): PostUserLoginUseCase {
        return PostUserLoginUseCase(repository)
    }

    @Provides
    @Singleton
    fun providesGetPetitionListUseCase(repository: PetitionRepository): GetPetitionListUseCase {
        return GetPetitionListUseCase(repository)
    }

    @Provides
    @Singleton
    fun providesPostPetitionUseCase(repository: PetitionRepository): PostPetitionUseCase {
        return PostPetitionUseCase(repository)
    }

    @Provides
    @Singleton
    fun providesPostConcurUseCase(repository: ConcurRepository): PostConcurUseCase {
        return PostConcurUseCase(repository)
    }

    @Provides
    @Singleton
    fun providesGetConcurListUseCase(repository: ConcurRepository): GetConcurListUseCase {
        return GetConcurListUseCase(repository)
    }

    @Provides
    @Singleton
    fun providesGetUserConcurListUseCase(repository: ConcurRepository): GetUserConcurListUseCase {
        return GetUserConcurListUseCase(repository)
    }

    @Provides
    @Singleton
    fun providesPostPetitionAnswerUseCase(repository: PetitionAnswerRepository): PostPetitionAnswerUseCase {
        return PostPetitionAnswerUseCase(repository)
    }

    @Provides
    @Singleton
    fun providesGetPetitionAnswerUseCase(repository: PetitionAnswerRepository): GetPetitionAnswerUseCase {
        return GetPetitionAnswerUseCase(repository)
    }

    @Provides
    @Singleton
    fun providesDeletePetitionAnswerUseCase(repository: PetitionAnswerRepository): DeletePetitionAnswerUseCase {
        return DeletePetitionAnswerUseCase(repository)
    }

    @Provides
    @Singleton
    fun providesPatchPetitionAnswerUseCase(repository: PetitionAnswerRepository): PatchPetitionAnswerUseCase {
        return PatchPetitionAnswerUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetCategoryUseCase(repository: CategoryRepository): GetCategoryUseCase {
        return GetCategoryUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetCompletedPetitionListUseCase(repository: PetitionRepository): GetCompletedPetitionListUseCase {
        return GetCompletedPetitionListUseCase(repository)
    }
}