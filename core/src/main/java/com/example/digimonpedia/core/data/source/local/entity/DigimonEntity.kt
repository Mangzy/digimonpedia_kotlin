package com.example.digimonpedia.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "digimon")
data class DigimonEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "remoteId")
    val remoteId: Int? = null,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "level")
    val level: String,

    @ColumnInfo(name = "img")
    val img: String,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false

)