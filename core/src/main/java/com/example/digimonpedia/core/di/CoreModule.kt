package com.example.digimonpedia.core.di

import androidx.room.Room
import com.example.digimonpedia.core.data.DigimonRepository
import com.example.digimonpedia.core.data.source.local.LocalDataSource
import com.example.digimonpedia.core.data.source.local.room.DigimonDatabase
import com.example.digimonpedia.core.data.source.remote.RemoteDataSource
import com.example.digimonpedia.core.data.source.remote.network.ApiService
import com.example.digimonpedia.core.domain.repository.IDigimonRepository
import com.example.digimonpedia.core.utils.AppExecutors
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<DigimonDatabase>().digimonDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            DigimonDatabase::class.java, "Digimon.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        okhttp3.OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        Retrofit.Builder()
            .baseUrl("https://digi-api.com/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
    }
    // Provide ApiService from Retrofit
    single<ApiService> { get<Retrofit>().create(ApiService::class.java) }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IDigimonRepository> { DigimonRepository(get(), get(), get()) }
}