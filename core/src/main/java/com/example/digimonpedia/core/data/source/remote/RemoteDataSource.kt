package com.example.digimonpedia.core.data.source.remote

import com.example.digimonpedia.core.data.source.remote.network.ApiResponse
import com.example.digimonpedia.core.data.source.remote.network.ApiService
import com.example.digimonpedia.core.data.source.remote.response.DigimonResponse
import com.example.digimonpedia.core.data.source.remote.response.DigiApiDetailResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getAllDigimon(): Flow<ApiResponse<List<DigimonResponse>>> =
        flow<ApiResponse<List<DigimonResponse>>> {
            val resp = apiService.getList(page = 0, pageSize = 50)
            val list = resp.content.map {
                DigimonResponse(
                    remoteId = it.id,
                    name = it.name,
                    img = it.image,
                    level = "" // Level tidak tersedia di list; bisa diisi saat detail fetch
                )
            }
            if (list.isNotEmpty()) emit(ApiResponse.Success(list)) else emit(ApiResponse.Empty)
        }.catch { e -> emit(ApiResponse.Error(e.message ?: e.toString())) }

    suspend fun getDigimonDetail(id: Int): Flow<ApiResponse<DigiApiDetailResponse>> =
        flow<ApiResponse<DigiApiDetailResponse>> {
            val resp = apiService.getDetail(id)
            emit(ApiResponse.Success(resp))
        }.catch { e -> emit(ApiResponse.Error(e.message ?: e.toString())) }
}