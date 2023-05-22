package com.marassu.petition.di

import com.marassu.petition.di.qualifiers.ForAuth
import com.marassu.petition.di.qualifiers.ForPublic
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object APIModule {
    private const val BASE_URL = "http://150.230.248.7:8080"
    @Provides
    @Singleton
    @ForPublic
    fun providePublicRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    @ForAuth
    fun provideAuthRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
}