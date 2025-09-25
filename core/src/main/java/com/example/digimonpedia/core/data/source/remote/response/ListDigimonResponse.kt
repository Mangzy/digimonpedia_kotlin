package com.example.digimonpedia.core.data.source.remote.response
import com.google.gson.annotations.SerializedName
data class ListDigimonResponse (

    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("data")
    val data: List<DigimonResponse>
)