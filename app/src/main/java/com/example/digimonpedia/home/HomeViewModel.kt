package com.example.digimonpedia.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.digimonpedia.core.domain.usecase.DigimonUseCase

class HomeViewModel(digimonUseCase: DigimonUseCase) : ViewModel() {
    val digimon = digimonUseCase.getAllDigimon().asLiveData()
}