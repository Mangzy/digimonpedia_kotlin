package com.example.digimonpedia.core.domain.repository

import com.example.digimonpedia.core.data.Resource
import com.example.digimonpedia.core.domain.model.Digimon
import com.example.digimonpedia.core.domain.model.DigimonDetail
import kotlinx.coroutines.flow.Flow

interface IDigimonRepository {

    fun getAllDigimon(): Flow<Resource<List<Digimon>>>

    fun getFavoriteDigimon(): Flow<List<Digimon>>

    fun setFavoriteDigimon(digimon: Digimon, state: Boolean)
    
    // New: load detail by remote id
    fun getDigimonDetail(remoteId: Int): Flow<Resource<DigimonDetail>>
}