package com.example.kotlinpro.data.network.dtos

import com.example.kotlinpro.common.base.IBaseDiffModel
import com.google.gson.annotations.SerializedName

data class LocationDto(

    @SerializedName("id")
    override val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("type")
    val type: String,

    @SerializedName("dimension")
    val dimension: String,

    @SerializedName("url")
    val url: String,

    @SerializedName("created")
    val created: String

) : IBaseDiffModel