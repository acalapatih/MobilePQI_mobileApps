package com.mobilepqi.core.data.source.remote

import com.mobilepqi.core.data.source.remote.network.ApiResponse
import com.mobilepqi.core.data.source.remote.network.ApiSholatService
import com.mobilepqi.core.data.source.remote.network.CommonService
import com.mobilepqi.core.data.source.remote.network.MobilePqiService
import com.mobilepqi.core.data.source.remote.response.jadwalsholat.JadwalSholatResponse
import com.mobilepqi.core.data.source.remote.response.lupapassword.LupaPasswordPayload
import com.mobilepqi.core.data.source.remote.response.lupapassword.LupaPasswordResponse
import com.mobilepqi.core.data.source.remote.response.menuqiroah.CreateMateriQiroahPayload
import com.mobilepqi.core.data.source.remote.response.menuqiroah.CreateMateriQiroahResponse
import com.mobilepqi.core.data.source.remote.response.menuqiroah.GetDetailMateriQiroahResponse
import com.mobilepqi.core.data.source.remote.response.menuqiroah.GetMateriQiroahResponse
import com.mobilepqi.core.data.source.remote.response.signin.SigninPayload
import com.mobilepqi.core.data.source.remote.response.signin.SigninResponse
import com.mobilepqi.core.data.source.remote.response.signup.SignupPayload
import com.mobilepqi.core.data.source.remote.response.signup.SignupResponse
import com.mobilepqi.core.data.source.remote.response.silabus.CreateSilabusPayload
import com.mobilepqi.core.data.source.remote.response.silabus.CreateSilabusResponse
import com.mobilepqi.core.data.source.remote.response.silabus.DeleteSilabusResponse
import com.mobilepqi.core.data.source.remote.response.silabus.GetSilabusResponse
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

    suspend fun createMateriQiroah(
        request: CreateMateriQiroahPayload,
        idKelas: Int
    ): Flow<ApiResponse<CreateMateriQiroahResponse>> {
        return flow {
            try {
                val response = mobilePqiService.createMateriQiroah(request, idKelas)
                if (response.status == 200) {
                    emit(ApiResponse.Success(response))
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.setGeneralError()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getMateriQiroah(idKelas : Int): Flow<ApiResponse<GetMateriQiroahResponse>> {
        return flow {
            try {
                val response =
                    mobilePqiService.getMateriQiroah(idKelas)
                if (response.status == 200) {
                    emit(ApiResponse.Success(response))
                }
            } catch (e: Throwable) {
                emit(ApiResponse.Error(e.setGeneralError()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getDetailMateriQiroah(id: Int): Flow<ApiResponse<GetDetailMateriQiroahResponse>> {
        return flow {
            try {
                val response =
                    mobilePqiService.getDetailMateriQiroah(id)
                if (response.status == 200) {
                    emit(ApiResponse.Success(response))
                }
            } catch (e: Throwable) {
                emit(ApiResponse.Error(e.setGeneralError()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun lupaPassword(request: LupaPasswordPayload): Flow<ApiResponse<LupaPasswordResponse>> {
        return flow {
            try {
                val response = mobilePqiService.lupaPassword(request)
                if (response.status == 200) {
                    emit(ApiResponse.Success(response))
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.setGeneralError()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun createSilabus(request: CreateSilabusPayload, idKelas: Int): Flow<ApiResponse<CreateSilabusResponse>> {
        return flow {
            try {
                val response = mobilePqiService.createSilabus(request, idKelas)
                if (response.status == 200) {
                    emit(ApiResponse.Success(response))
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.setGeneralError()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getSilabus(idKelas: Int): Flow<ApiResponse<GetSilabusResponse>> {
        return flow {
            try {
                val response = mobilePqiService.getSilabus(idKelas)
                if (response.status == 200) {
                    emit(ApiResponse.Success(response))
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.setGeneralError()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun deleteSilabus(idKelas: Int): Flow<ApiResponse<DeleteSilabusResponse>> {
        return flow {
            try {
                val response = mobilePqiService.deleteSilabus(idKelas)
                if (response.status == 200) {
                    emit(ApiResponse.Success(response))
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.setGeneralError()))
            }
        }.flowOn(Dispatchers.IO)
    }
}