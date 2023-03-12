package com.mobilepqi.core.data.source.remote

import com.mobilepqi.core.data.source.remote.network.ApiResponse
import com.mobilepqi.core.data.source.remote.network.ApiSholatService
import com.mobilepqi.core.data.source.remote.network.CommonService
import com.mobilepqi.core.data.source.remote.network.MobilePqiService
import com.mobilepqi.core.data.source.remote.response.CreateNilaiResponse
import com.mobilepqi.core.data.source.remote.response.buatkelas.BuatKelasPayload
import com.mobilepqi.core.data.source.remote.response.buatkelas.BuatKelasResponse
import com.mobilepqi.core.data.source.remote.response.daftarkelas.DaftarKelasResponse
import com.mobilepqi.core.data.source.remote.response.detailkelas.DetailKelasResponse
import com.mobilepqi.core.data.source.remote.response.ibadah.*
import com.mobilepqi.core.data.source.remote.response.jadwalsholat.JadwalSholatResponse
import com.mobilepqi.core.data.source.remote.response.lupapassword.LupaPasswordPayload
import com.mobilepqi.core.data.source.remote.response.lupapassword.LupaPasswordResponse
import com.mobilepqi.core.data.source.remote.response.qiroah.*
import com.mobilepqi.core.data.source.remote.response.signin.SigninPayload
import com.mobilepqi.core.data.source.remote.response.signin.SigninResponse
import com.mobilepqi.core.data.source.remote.response.signup.SignupPayload
import com.mobilepqi.core.data.source.remote.response.signup.SignupResponse
import com.mobilepqi.core.data.source.remote.response.silabus.CreateSilabusPayload
import com.mobilepqi.core.data.source.remote.response.silabus.CreateSilabusResponse
import com.mobilepqi.core.data.source.remote.response.silabus.DeleteSilabusResponse
import com.mobilepqi.core.data.source.remote.response.silabus.GetSilabusResponse
import com.mobilepqi.core.data.source.remote.response.tambahdosen.GetTambahDosenResponse
import com.mobilepqi.core.data.source.remote.response.tambahdosen.PostTambahDosenPayload
import com.mobilepqi.core.data.source.remote.response.tambahdosen.PostTambahDosenResponse
import com.mobilepqi.core.data.source.remote.response.tugas.*
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
    private val mobilePqiService: MobilePqiService,
) {
    suspend fun getJadwalSholat(
        timestamp: String,
        latitude: String,
        longitude: String,
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
        file: MultipartBody.Part,
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

    suspend fun getTambahDosen(idKelas: Int): Flow<ApiResponse<GetTambahDosenResponse>> {
        return flow {
            try {
                val response = mobilePqiService.getTambahDosen(idKelas)
                if (response.status == 200) {
                    emit(ApiResponse.Success(response))
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.setGeneralError()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun postTambahDosen(
        request: PostTambahDosenPayload,
        idKelas: Int,
    ): Flow<ApiResponse<PostTambahDosenResponse>> {
        return flow {
            try {
                val response = mobilePqiService.postTambahdosen(request, idKelas)
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

    suspend fun detailkelas(idKelas: Int): Flow<ApiResponse<DetailKelasResponse>> {
        return flow {
            try {
                val response = mobilePqiService.detailkelas(idKelas)
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
        idKelas: Int,
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

    suspend fun getMateriQiroah(idKelas: Int): Flow<ApiResponse<GetMateriQiroahResponse>> {
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

    suspend fun deleteMateriQiroah(idMateri: Int): Flow<ApiResponse<DeleteMateriQiroahResponse>> {
        return flow {
            try {
                val response =
                    mobilePqiService.deleteMateriQiroah(idMateri)
                if (response.status == 200) {
                    emit(ApiResponse.Success(response))
                }
            } catch (e: Throwable) {
                emit(ApiResponse.Error(e.setGeneralError()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun updateDetailMateriQiroah(
        request: UpdateDetailMateriQiroahPayload,
        idMateri: Int,
    ): Flow<ApiResponse<UpdateDetailMateriQiroahResponse>> {
        return flow {
            try {
                val response = mobilePqiService.updateDetailMateriQiroah(request, idMateri)
                if (response.status == 200) {
                    emit(ApiResponse.Success(response))
                }
            } catch (e: Exception) {
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

    suspend fun createSilabus(
        request: CreateSilabusPayload,
        idKelas: Int,
    ): Flow<ApiResponse<CreateSilabusResponse>> {
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

    suspend fun createMateriIbadah(
        request: CreateMateriIbadahPayload,
        idKelas: Int,
    ): Flow<ApiResponse<CreateMateriIbadahResponse>> {
        return flow {
            try {
                val response = mobilePqiService.createMateriIbadah(request, idKelas)
                if (response.status == 200) {
                    emit(ApiResponse.Success(response))
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.setGeneralError()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getMateriIbadah(idKelas: Int): Flow<ApiResponse<GetMateriIbadahResponse>> {
        return flow {
            try {
                val response =
                    mobilePqiService.getMateriIbadah(idKelas)
                if (response.status == 200) {
                    emit(ApiResponse.Success(response))
                }
            } catch (e: Throwable) {
                emit(ApiResponse.Error(e.setGeneralError()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getDetailMateriIbadah(id: Int): Flow<ApiResponse<GetDetailMateriIbadahResponse>> {
        return flow {
            try {
                val response =
                    mobilePqiService.getDetailMateriIbadah(id)
                if (response.status == 200) {
                    emit(ApiResponse.Success(response))
                }
            } catch (e: Throwable) {
                emit(ApiResponse.Error(e.setGeneralError()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun deleteMateriIbadah(idMateri: Int): Flow<ApiResponse<DeleteMateriIbadahResponse>> {
        return flow {
            try {
                val response =
                    mobilePqiService.deleteMateriIbadah(idMateri)
                if (response.status == 200) {
                    emit(ApiResponse.Success(response))
                }
            } catch (e: Throwable) {
                emit(ApiResponse.Error(e.setGeneralError()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun updateDetailMateriIbadah(
        request: UpdateDetailMateriIbadahPayload,
        idMateri: Int,
    ): Flow<ApiResponse<UpdateDetailMateriIbadahResponse>> {
        return flow {
            try {
                val response = mobilePqiService.updateDetailMateriIbadah(request, idMateri)
                if (response.status == 200) {
                    emit(ApiResponse.Success(response))
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.setGeneralError()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getListTugas(idKelas: Int): Flow<ApiResponse<GetListTugasResponse>> {
        return flow {
            try {
                val response = mobilePqiService.getListTugas(idKelas)
                if (response.status == 200) {
                    emit(ApiResponse.Success(response))
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.setGeneralError()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun createTugas(
        request: CreateTugasPayload,
        idKelas: Int,
    ): Flow<ApiResponse<CreateTugasResponse>> {
        return flow {
            try {
                val response = mobilePqiService.createTugas(request, idKelas)
                if (response.status == 200) {
                    emit(ApiResponse.Success(response))
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.setGeneralError()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getListTopicTugas(
        idKelas: Int,
        topic: String,
    ): Flow<ApiResponse<GetListTopicTugasResponse>> {
        return flow {
            try {
                val response = mobilePqiService.getListTopicTugas(idKelas, topic)
                if (response.status == 200) {
                    emit(ApiResponse.Success(response))
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.setGeneralError()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getDetailTugas(idTugas: Int): Flow<ApiResponse<GetDetailTugasResponse>> {
        return flow {
            try {
                val response = mobilePqiService.getDetailTugas(idTugas)
                if (response.status == 200) {
                    emit(ApiResponse.Success(response))
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.setGeneralError()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun updateDetailTugas(
        request: UpdateDetailTugasPayload,
        idTugas: Int,
    ): Flow<ApiResponse<UpdateDetailTugasResponse>> {
        return flow {
            try {
                val response = mobilePqiService.updateDetailTugas(request, idTugas)
                if (response.status == 200) {
                    emit(ApiResponse.Success(response))
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.setGeneralError()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getListTugasMahasiswa(
        idTugas: Int,
        page: Int,
        limit: Int
    ): Flow<ApiResponse<GetListTugasMahasiswaResponse>> {
        return flow {
            try {
                val response = mobilePqiService.getListTugasMahasiswa(idTugas, page, limit)
                if (response.status == 200) {
                    emit(ApiResponse.Success(response))
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.setGeneralError()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getJawabanForDosen(
        idTugas: Int,
        nim: String
    ): Flow<ApiResponse<GetJawabanForDosenResponse>> {
        return flow {
            try {
                val response = mobilePqiService.getJawabanForDosen(idTugas, nim)
                if (response.status == 200) {
                    emit(ApiResponse.Success(response))
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.setGeneralError()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun createNilai(
        request: CreateNilaiPayload,
        idJawaban: Int
    ): Flow<ApiResponse<CreateNilaiResponse>> {
        return flow {
            try {
                val response = mobilePqiService.createNilai(request, idJawaban)
                if (response.status == 200) {
                    emit(ApiResponse.Success(response))
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.setGeneralError()))
            }
        }.flowOn(Dispatchers.IO)
    }
}