package com.example.digimonpedia

import android.app.Application
import com.example.digimonpedia.core.di.databaseModule
import com.example.digimonpedia.core.di.networkModule
import com.example.digimonpedia.core.di.repositoryModule
import com.example.digimonpedia.di.useCaseModule
import com.example.digimonpedia.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}