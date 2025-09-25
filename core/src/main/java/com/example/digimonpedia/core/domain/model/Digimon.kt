package com.example.digimonpedia.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Digimon(
    // Local DB id
    val id: Int,
    // Remote API id from Digi API
    val remoteId: Int? = null,
    val name: String,
    val level: String,
    val img: String,
    var isFavorite: Boolean = false
) : Parcelable