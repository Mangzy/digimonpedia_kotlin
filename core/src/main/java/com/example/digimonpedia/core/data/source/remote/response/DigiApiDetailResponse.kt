package com.example.digimonpedia.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class DigiApiDetailResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("images") val images: List<DigiApiImage>?,
    @SerializedName("levels") val levels: List<DigiApiLevel>?,
    @SerializedName("descriptions") val descriptions: List<DigiApiDescription>?
)

data class DigiApiImage(
    @SerializedName("href") val href: String,
    @SerializedName("transparent") val transparent: Boolean
)

data class DigiApiLevel(
    @SerializedName("id") val id: Int,
    @SerializedName("level") val level: String
)

data class DigiApiDescription(
    @SerializedName("origin") val origin: String,
    @SerializedName("language") val language: String,
    @SerializedName("description") val description: String
)
