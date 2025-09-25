package com.example.digimonpedia.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.digimonpedia.core.domain.usecase.DigimonUseCase

class FavoriteViewModel(private val digimonUseCase: DigimonUseCase) : ViewModel() {
    val favorites = digimonUseCase.getFavoriteDigimon().asLiveData()
}