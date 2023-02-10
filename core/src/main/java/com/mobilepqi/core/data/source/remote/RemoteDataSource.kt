package com.mobilepqi.core.data.source.remote

import com.mobilepqi.core.data.source.remote.network.ApiResponse
import com.mobilepqi.core.data.source.remote.network.ApiSholatService
import com.mobilepqi.core.data.source.remote.network.CommonService
import com.mobilepqi.core.data.source.remote.response.jadwalsholat.JadwalSholatResponse
import com.mobilepqi.core.data.source.remote.response.uploadimage.UploadImageResponse
import com.mobilepqi.core.util.setGeneralError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.MultipartBody

class RemoteDataSource(
    private val apiSholatService: ApiSholatService,
    private val commonService: CommonService
) {
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
            } catch (e: Throwable) {
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun uploadImage(
        file: MultipartBody.Part
    ): Flow<ApiResponse<UploadImageResponse>> {
        return channelFlow {
            try {
                val response = commonService.uploadImage(file = file)
                if (response.status == 200) {
                    send(ApiResponse.Success(response))
                }
            } catch (e: Throwable) {
                send(ApiResponse.Error(e.setGeneralError()))
            }
        }
    }
}