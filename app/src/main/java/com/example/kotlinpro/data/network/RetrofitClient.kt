package com.example.kotlinpro.data.network

import com.example.kotlinpro.common.constants.Constants
import com.example.kotlinpro.data.network.apiservise.CharacterApiService
import com.example.kotlinpro.data.network.apiservise.EpisodeApiService
import com.example.kotlinpro.data.network.apiservise.LocationApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClient {

    private val okHttpClient: OkHttpClient = OkHttpClient()
        .newBuilder()
        .addInterceptor(provideLoggingInterceptor()!!)
        .connectTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .build()

    private val provideRetrofitClient = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()


    private fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    fun provideCharacterRetrofitClient(): CharacterApiService {
        return provideRetrofitClient.create(CharacterApiService::class.java)
    }

    fun provideEpisodeRetrofitClient(): EpisodeApiService {
        return provideRetrofitClient.create(EpisodeApiService::class.java)
    }

    fun provideLocationRetrofitClient(): LocationApiService {
        return provideRetrofitClient.create(LocationApiService::class.java)
    }
}