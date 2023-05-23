package com.marassu.petition.di

import com.marassu.petition.di.qualifiers.ForAccessToken
import com.marassu.petition.di.qualifiers.ForLogging
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object APIModule {
    private const val BASE_URL = "http://150.230.248.7:8080"
    @Provides
    @Singleton
    fun provideRetrofitPair(@ForLogging loggingOkHttpClient: OkHttpClient,
                            @ForAccessToken accessTokenHttpClient: OkHttpClient): Pair<Retrofit, Retrofit> {
        val publicRetrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(loggingOkHttpClient)
            .build()

        val authRetrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(accessTokenHttpClient)
            .build()

        return Pair(publicRetrofit, authRetrofit)
    }
}