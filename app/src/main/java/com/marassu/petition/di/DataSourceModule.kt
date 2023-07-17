package com.marassu.petition.di

import com.marassu.data.source.CategoryRemoteDataSource
import com.marassu.data.source.ConcurRemoteDataSource
import com.marassu.data.source.PetitionAnswerRemoteDataSource
import com.marassu.data.source.PetitionRemoteDataSource
import com.marassu.data.source.UserRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    @Provides
    @Singleton
    fun providesUserRemoteDataSource(retrofitPair: Pair<Retrofit, Retrofit>): UserRemoteDataSource {
        return UserRemoteDataSource(retrofitPair)
    }

    @Provides
    fun providesPetitionRemoteDataSource(retrofitPair: Pair<Retrofit, Retrofit>): PetitionRemoteDataSource {
        return PetitionRemoteDataSource(retrofitPair)
    }

    @Provides
    @Singleton
    fun providesConcurRemoteDataSource(retrofitPair: Pair<Retrofit, Retrofit>): ConcurRemoteDataSource {
        return ConcurRemoteDataSource(retrofitPair)
    }

    @Provides
    @Singleton
    fun providesPetitionAnswerRemoteDataSource(retrofitPair: Pair<Retrofit, Retrofit>): PetitionAnswerRemoteDataSource {
        return PetitionAnswerRemoteDataSource(retrofitPair)
    }

    @Provides
    @Singleton
    fun provideCategoryRemoteDataSource(retrofitPair: Pair<Retrofit, Retrofit>): CategoryRemoteDataSource {
        return CategoryRemoteDataSource(retrofitPair)
    }
}