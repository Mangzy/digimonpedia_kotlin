package com.example.digimonpedia.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class DigiApiListResponse(
    @SerializedName("content") val content: List<DigiApiItem>,
    @SerializedName("pageable") val pageable: DigiApiPage
)

data class DigiApiItem(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("href") val href: String,
    @SerializedName("image") val image: String
)

data class DigiApiPage(
    @SerializedName("currentPage") val currentPage: Int,
    @SerializedName("elementsOnPage") val elementsOnPage: Int,
    @SerializedName("totalElements") val totalElements: Int,
    @SerializedName("totalPages") val totalPages: Int,
    @SerializedName("previousPage") val previousPage: String?,
    @SerializedName("nextPage") val nextPage: String?
)
