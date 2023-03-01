package com.mobilepqi.core.data.source.remote

import com.mobilepqi.core.data.source.remote.network.ApiResponse
import com.mobilepqi.core.data.source.remote.network.ApiSholatService
import com.mobilepqi.core.data.source.remote.network.CommonService
import com.mobilepqi.core.data.source.remote.network.MobilePqiService
import com.mobilepqi.core.data.source.remote.response.buatkelas.BuatKelasPayload
import com.mobilepqi.core.data.source.remote.response.buatkelas.BuatKelasResponse
import com.mobilepqi.core.data.source.remote.response.daftarkelas.DaftarKelasResponse
import com.mobilepqi.core.data.source.remote.response.detailkelas.DetailKelasResponse
import com.mobilepqi.core.data.source.remote.response.jadwalsholat.JadwalSholatResponse
import com.mobilepqi.core.data.source.remote.response.signin.SigninPayload
import com.mobilepqi.core.data.source.remote.response.signin.SigninResponse
import com.mobilepqi.core.data.source.remote.response.signup.SignupPayload
import com.mobilepqi.core.data.source.remote.response.signup.SignupResponse
import com.mobilepqi.core.data.source.remote.response.tambahdosen.TambahDosenResponse
import com.mobilepqi.core.data.source.remote.response.uploadimage.UploadResponse
import com.mobilepqi.core.util.setGeneralError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.MultipartBody

class RemoteDataSource(
    private val apiSholatService: ApiSholatService,
    private val commonService: CommonService,
    private val mobilePqiService: MobilePqiService
) {
    suspend fun getJadwalSholat(
        timestamp: String,
        latitude: String,
        longitude: String
    ): Flow<ApiResponse<JadwalSholatResponse>> {
        return flow {
            try {
                val response = apiSholatService.getJadwalSholat(
                    timestamp = timestamp,
                    latitude = latitude,
                    longitude = longitude
                )
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

    suspend fun uploadFileOrImage(
        file: MultipartBody.Part
    ): Flow<ApiResponse<UploadResponse>> {
        return channelFlow {
            try {
                val response = commonService.uploadToServer(file = file)
                if (response.status == 200) {
                    send(ApiResponse.Success(response))
                }
            } catch (e: Throwable) {
                send(ApiResponse.Error(e.setGeneralError()))
            }
        }
    }

    suspend fun signin(request: SigninPayload): Flow<ApiResponse<SigninResponse>> {
        return flow {
            try {
                val response = mobilePqiService.signin(request)
                if (response.status == 200) {
                    emit(ApiResponse.Success(response))
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.setGeneralError()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun signup(request: SignupPayload): Flow<ApiResponse<SignupResponse>> {
        return flow {
            try {
                val response = mobilePqiService.signup(request)
                if (response.status == 200) {
                    emit(ApiResponse.Success(response))
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.setGeneralError()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun tambahdosen(): Flow<ApiResponse<TambahDosenResponse>> {
        return flow {
            try {
                val response = mobilePqiService.tambahdosen()
                if (response.status == 200) {
                    emit(ApiResponse.Success(response))
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.setGeneralError()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun buatkelas(request: BuatKelasPayload): Flow<ApiResponse<BuatKelasResponse>> {
        return flow {
            try {
                val response = mobilePqiService.buatkelas(request)
                if (response.status == 200) {
                    emit(ApiResponse.Success(response))
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.setGeneralError()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun daftarkelas(): Flow<ApiResponse<DaftarKelasResponse>> {
        return flow {
            try {
                val response = mobilePqiService.daftarkelas()
                if (response.status == 200) {
                    emit(ApiResponse.Success(response))
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.setGeneralError()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun detailkelas(): Flow<ApiResponse<DetailKelasResponse>> {
        return flow {
            try {
                val response = mobilePqiService.detailkelas()
                if (response.status == 200) {
                    emit(ApiResponse.Success(response))
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.setGeneralError()))
            }
        }.flowOn(Dispatchers.IO)
    }
}