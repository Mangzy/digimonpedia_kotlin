package com.example.digimonpedia.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.digimonpedia.core.data.source.local.entity.DigimonEntity
import kotlinx.coroutines.flow.Flow
@Dao
interface DigimonDao {

    @Query("SELECT * FROM digimon")
    fun getAllDigimon(): Flow<List<DigimonEntity>>

    @Query("SELECT * FROM digimon where isFavorite = 1")
    fun getFavoriteDigimon(): Flow<List<DigimonEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDigimon(digimon: List<DigimonEntity>)

    @Update
    fun updateFavoriteDigimon(digimon: DigimonEntity)
}