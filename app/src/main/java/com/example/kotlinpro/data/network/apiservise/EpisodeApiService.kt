package com.example.kotlinpro.data.network.apiservise

import com.example.kotlinpro.data.network.dtos.EpisodeDto
import com.example.kotlinpro.data.network.dtos.RickAndMortyResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface EpisodeApiService {

    @GET("episode")
    suspend fun fetchEpisodesApiService(
        @Query("page") page: Int) : RickAndMortyResponse<EpisodeDto>

    @GET("episode/{id}")
    suspend fun fetchEpisodeApiService(
        @Path("id") id: Int) : EpisodeDto
}