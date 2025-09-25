package com.example.digimonpedia.di

import com.example.digimonpedia.core.domain.usecase.DigimonInteractor
import com.example.digimonpedia.core.domain.usecase.DigimonUseCase
import com.example.digimonpedia.detail.DetailViewModel
import com.example.digimonpedia.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<DigimonUseCase> { DigimonInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}