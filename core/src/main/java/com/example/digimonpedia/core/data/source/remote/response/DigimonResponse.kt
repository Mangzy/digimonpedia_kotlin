package com.example.digimonpedia.core.data.source.remote.response
import com.google.gson.annotations.SerializedName
data class DigimonResponse (
    // Remote API id (from Digi API list)
    @SerializedName("id")
    val remoteId: Int? = null,

    @SerializedName("name")
    val name: String,

    // Old API used key 'img'; new Digi API uses 'image'
    @SerializedName(value = "img", alternate = ["image"])
    val img: String,

    @SerializedName("level")
    val level: String = ""
)