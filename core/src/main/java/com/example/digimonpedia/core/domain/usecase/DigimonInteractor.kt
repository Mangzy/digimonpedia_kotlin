package com.example.digimonpedia.core.domain.usecase

import com.example.digimonpedia.core.data.Resource
import com.example.digimonpedia.core.domain.model.DigimonDetail
import com.example.digimonpedia.core.domain.repository.IDigimonRepository
import com.example.digimonpedia.core.domain.model.Digimon

class DigimonInteractor(private val digimonRepository: IDigimonRepository) : DigimonUseCase {
    override fun getAllDigimon() = digimonRepository.getAllDigimon()

    override fun getFavoriteDigimon() = digimonRepository.getFavoriteDigimon()

    override fun setFavoriteDigimon(digimon: Digimon, newState: Boolean) =
        digimonRepository.setFavoriteDigimon(digimon, newState)

    override fun getDigimonDetail(remoteId: Int): kotlinx.coroutines.flow.Flow<Resource<DigimonDetail>> =
        digimonRepository.getDigimonDetail(remoteId)
}