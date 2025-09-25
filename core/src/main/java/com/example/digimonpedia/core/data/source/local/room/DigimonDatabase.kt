package com.example.digimonpedia.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.digimonpedia.core.data.source.local.entity.DigimonEntity

@Database(entities = [DigimonEntity::class], version = 2, exportSchema = false)
abstract class DigimonDatabase : RoomDatabase() {

    abstract fun digimonDao(): DigimonDao
}