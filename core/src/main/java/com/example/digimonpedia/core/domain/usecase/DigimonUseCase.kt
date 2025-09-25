package com.example.digimonpedia.core.domain.usecase

import com.example.digimonpedia.core.data.Resource
import com.example.digimonpedia.core.domain.model.Digimon
import com.example.digimonpedia.core.domain.model.DigimonDetail
import kotlinx.coroutines.flow.Flow

interface DigimonUseCase {
    fun getAllDigimon(): Flow<Resource<List<Digimon>>>

    fun getFavoriteDigimon(): Flow<List<Digimon>>

    fun setFavoriteDigimon(digimon: Digimon, state: Boolean)

    fun getDigimonDetail(remoteId: Int): Flow<Resource<DigimonDetail>>
}