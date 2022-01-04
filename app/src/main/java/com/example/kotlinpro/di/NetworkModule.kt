package com.example.kotlinpro.di

import com.example.kotlinpro.data.network.RetrofitClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    val retrofitClient = RetrofitClient()

    @Provides
    @Singleton
    fun provideCharacterApiService() = retrofitClient.provideCharacterRetrofitClient()

    @Provides
    @Singleton
    fun provideEpisodeApiService() = retrofitClient.provideEpisodeRetrofitClient()

    @Provides
    @Singleton
    fun provideLocationApiService() = retrofitClient.provideLocationRetrofitClient()
}