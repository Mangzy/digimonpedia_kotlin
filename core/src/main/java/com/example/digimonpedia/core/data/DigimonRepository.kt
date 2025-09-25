package com.example.digimonpedia.core.data

import com.example.digimonpedia.core.data.source.local.LocalDataSource
import com.example.digimonpedia.core.data.source.remote.RemoteDataSource
import com.example.digimonpedia.core.data.source.remote.network.ApiResponse
import com.example.digimonpedia.core.data.source.remote.response.DigimonResponse
import com.example.digimonpedia.core.domain.model.Digimon
import com.example.digimonpedia.core.domain.model.DigimonDetail
import com.example.digimonpedia.core.domain.repository.IDigimonRepository
import com.example.digimonpedia.core.utils.AppExecutors
import com.example.digimonpedia.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.first

class DigimonRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors,
): IDigimonRepository {

    override fun getAllDigimon(): Flow<Resource<List<Digimon>>> =
        object : NetworkBoundResource<List<Digimon>, List<DigimonResponse>>() {
            override fun loadFromDB(): Flow<List<Digimon>> {
                return localDataSource.getAllDigimon().map { DataMapper.mapEntitiesToDomain(it) }
            }

            override fun shouldFetch(data: List<Digimon>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<DigimonResponse>>> =
                remoteDataSource.getAllDigimon()

            override suspend fun saveCallResult(data: List<DigimonResponse>) {
                val digimonList = DataMapper.mapResponseToEntities(data)
                localDataSource.insertDigimon(digimonList)
            }
        }.asFlow()

    override fun getFavoriteDigimon(): Flow<List<Digimon>> {
        return localDataSource.getFavoriteDigimon().map { DataMapper.mapEntitiesToDomain(it) }
    }

    override fun setFavoriteDigimon(digimon: Digimon, state: Boolean) {
        val digimonEntity = DataMapper.mapDomainToEntity(digimon)
        appExecutors.diskIO().execute { localDataSource.updateFavoriteDigimon(digimonEntity, state) }
    }

    override fun getDigimonDetail(remoteId: Int): Flow<Resource<DigimonDetail>> = flow {
        emit(Resource.Loading())
        when (val api = remoteDataSource.getDigimonDetail(remoteId).first()) {
            is ApiResponse.Success -> emit(Resource.Success(DataMapper.mapDetailToDomain(api.data)))
            is ApiResponse.Empty -> emit(Resource.Error("No detail"))
            is ApiResponse.Error -> emit(Resource.Error(api.errorMessage))
        }
    }
}