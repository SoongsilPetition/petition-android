package com.marassu.petition.di

import com.marassu.data.source.ConcurRemoteDataSource
import com.marassu.data.source.PetitionRemoteDataSource
import com.marassu.data.source.UserRemoteDataSource
import com.marassu.petition.di.qualifiers.ForAuth
import com.marassu.petition.di.qualifiers.ForPublic
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
    fun providesUserRemoteDataSource(@ForPublic retrofit: Retrofit): UserRemoteDataSource {
        return UserRemoteDataSource(retrofit)
    }

    @Provides
    @Singleton
    fun providesPetitionRemoteDataSource(@ForAuth retrofit: Retrofit): PetitionRemoteDataSource {
        return PetitionRemoteDataSource(retrofit)
    }

    @Provides
    @Singleton
    fun providesConcurRemoteDataSource(@ForAuth retrofit: Retrofit): ConcurRemoteDataSource {
        return ConcurRemoteDataSource(retrofit)
    }
}