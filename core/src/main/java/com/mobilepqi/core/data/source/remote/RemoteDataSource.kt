package com.mobilepqi.core.data.source.remote

import com.mobilepqi.core.data.source.remote.network.ApiResponse
import com.mobilepqi.core.data.source.remote.network.ApiSholatService
import com.mobilepqi.core.data.source.remote.response.JadwalSholatResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiSholatService: ApiSholatService) {
    suspend fun getJadwalSholat(
        latitude: String,
        longitude: String
    ): Flow<ApiResponse<JadwalSholatResponse>> {
        return flow {
            try {
                val response =
                    apiSholatService.getJadwalSholat(latitude = latitude, longitude = longitude)
                val code = response.code
                if (code == 200) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Error(response.status ?: ""))
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }
}