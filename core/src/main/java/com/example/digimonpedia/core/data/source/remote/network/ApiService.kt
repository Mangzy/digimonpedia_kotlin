package com.example.digimonpedia.core.data.source.remote.network

import com.example.digimonpedia.core.data.source.remote.response.DigiApiDetailResponse
import com.example.digimonpedia.core.data.source.remote.response.DigiApiListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    // https://digi-api.com/api/v1/digimon?page=0&pageSize=20
    @GET("digimon")
    suspend fun getList(
        @Query("page") page: Int = 0,
        @Query("pageSize") pageSize: Int = 20
    ): DigiApiListResponse

    // https://digi-api.com/api/v1/digimon/{id}
    @GET("digimon/{id}")
    suspend fun getDetail(@Path("id") id: Int): DigiApiDetailResponse
}