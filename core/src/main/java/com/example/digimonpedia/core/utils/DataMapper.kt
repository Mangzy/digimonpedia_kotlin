package com.example.digimonpedia.core.utils

import com.example.digimonpedia.core.data.source.local.entity.DigimonEntity
import com.example.digimonpedia.core.data.source.remote.response.DigimonResponse
import com.example.digimonpedia.core.domain.model.Digimon
import com.example.digimonpedia.core.domain.model.DigimonDetail
import com.example.digimonpedia.core.data.source.remote.response.DigiApiDetailResponse

object DataMapper {
    fun mapResponseToEntities(input: List<DigimonResponse>): List<DigimonEntity> {
        val digimonList = ArrayList<DigimonEntity>()
        input.map {
            val digimon = DigimonEntity(
                remoteId = it.remoteId,
                name = it.name,
                img = it.img,
                level = it.level,
                isFavorite = false
            )
            digimonList.add(digimon)
        }
        return digimonList
    }

    fun mapEntitiesToDomain(input: List<DigimonEntity>): List<Digimon> = input.map {
        Digimon(
            id = it.id,
            remoteId = it.remoteId,
            name = it.name,
            img = it.img,
            level = it.level,
            isFavorite = it.isFavorite
        )
    }

    fun mapDomainToEntity(input: Digimon) = DigimonEntity(
        id = input.id,
        remoteId = input.remoteId,
        name = input.name,
        img = input.img,
        level = input.level,
        isFavorite = input.isFavorite
    )

    // Map API detail to domain detail
    fun mapDetailToDomain(input: DigiApiDetailResponse): DigimonDetail = DigimonDetail(
        id = input.id,
        name = input.name,
        image = input.images?.firstOrNull()?.href,
        level = input.levels?.firstOrNull()?.level,
        description = input.descriptions?.firstOrNull { it.language == "en_us" }?.description
            ?: input.descriptions?.firstOrNull()?.description
    )
}