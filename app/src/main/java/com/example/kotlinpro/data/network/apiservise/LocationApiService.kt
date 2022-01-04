package com.example.kotlinpro.data.network.apiservise

import com.example.kotlinpro.data.network.dtos.LocationDto
import com.example.kotlinpro.data.network.dtos.RickAndMortyResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface LocationApiService {

    @GET("location")
    suspend fun fetchLocationsApiService(
        @Query("page") page: Int) : RickAndMortyResponse<LocationDto>

    @GET("location/{id}")
    suspend fun fetchLocationApiService(
        @Path("id") id: Int) : LocationDto
}