package com.example.kotlinpro.data.network.dtos

import com.google.gson.annotations.SerializedName

class RickAndMortyResponse<T> (

    @SerializedName("info")
    val info: Info,

    @SerializedName("results")
    val results: List<T>
)