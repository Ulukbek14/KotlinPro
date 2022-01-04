package com.example.kotlinpro.data.network.dtos

import com.example.kotlinpro.common.base.IBaseDiffModel
import com.google.gson.annotations.SerializedName

data class CharacterDto(

    @SerializedName("image")
    val image: String,

    @SerializedName("id")
    override val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("status")
    val status: String,

    @SerializedName("species")
    val species: String,

    @SerializedName("type")
    val type: String,

    @SerializedName("gender")
    val gender: String

) : IBaseDiffModel