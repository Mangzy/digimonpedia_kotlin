package com.example.digimonpedia.core.data.source.local

import com.example.digimonpedia.core.data.source.local.entity.DigimonEntity
import com.example.digimonpedia.core.data.source.local.room.DigimonDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val digimonDao: DigimonDao) {

    fun getAllDigimon() : Flow<List<DigimonEntity>> = digimonDao.getAllDigimon()

    fun getFavoriteDigimon() : Flow<List<DigimonEntity>> = digimonDao.getFavoriteDigimon()

    suspend fun insertDigimon(digimon: List<DigimonEntity>) =
        digimonDao.insertDigimon(digimon)

    fun updateFavoriteDigimon(digimon: DigimonEntity, newState: Boolean) {
        digimon.isFavorite = newState
        digimonDao.updateFavoriteDigimon(digimon)
    }
}