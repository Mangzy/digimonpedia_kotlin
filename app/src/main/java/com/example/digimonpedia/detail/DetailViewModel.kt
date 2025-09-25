package com.example.digimonpedia.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.digimonpedia.core.domain.model.Digimon
import com.example.digimonpedia.core.domain.usecase.DigimonUseCase

class DetailViewModel(private val digimonUseCase: DigimonUseCase) : ViewModel()  {
    fun setFavoriteDigimon(digimon: Digimon, newState: Boolean) {
        digimonUseCase.setFavoriteDigimon(digimon, newState)
    }

    fun getDetail(remoteId: Int) = digimonUseCase.getDigimonDetail(remoteId).asLiveData()
}
