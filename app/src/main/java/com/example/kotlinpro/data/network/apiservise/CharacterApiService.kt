package com.example.kotlinpro.data.network.apiservise

import com.example.kotlinpro.data.network.dtos.CharacterDto
import com.example.kotlinpro.data.network.dtos.RickAndMortyResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterApiService {

    @GET("character")
    suspend fun fetchCharactersApiService(
        @Query("page") page: Int): RickAndMortyResponse<CharacterDto>

    @GET("character/{id}")
    suspend fun fetchCharacterApiService(
        @Path("id") id: Int) : CharacterDto
}