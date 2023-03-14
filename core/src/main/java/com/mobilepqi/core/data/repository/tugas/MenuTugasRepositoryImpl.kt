package com.mobilepqi.core.data.repository.tugas

import com.mobilepqi.core.data.NetworkOnlyResource
import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.data.source.remote.RemoteDataSource
import com.mobilepqi.core.data.source.remote.network.ApiResponse
import com.mobilepqi.core.data.source.remote.response.tugas.CreateNilaiResponse
import com.mobilepqi.core.data.source.remote.response.tugas.*
import com.mobilepqi.core.domain.model.tugas.*
import com.mobilepqi.core.domain.repository.tugas.MenuTugasRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class MenuTugasRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
) : MenuTugasRepository {
    override fun getListTugas(idKelas: Int): Flow<Resource<GetListTugasModel>> =
        object : NetworkOnlyResource<GetListTugasModel, GetListTugasResponse>() {
            override fun loadFromNetwork(data: GetListTugasResponse): Flow<GetListTugasModel> =
                GetListTugasModel.mapResponseToModel(data)

            override suspend fun createCall(): Flow<ApiResponse<GetListTugasResponse>> =
                remoteDataSource.getListTugas(idKelas)
        }.asFlow()

    override fun createTugas(payload: CreateTugasPayload, idKelas: Int): Flow<Resource<Boolean>> =
        object : NetworkOnlyResource<Boolean, CreateTugasResponse>() {
            override fun loadFromNetwork(data: CreateTugasResponse): Flow<Boolean> =
                flowOf(true)

            override suspend fun createCall(): Flow<ApiResponse<CreateTugasResponse>> =
                remoteDataSource.createTugas(payload, idKelas)
        }.asFlow()

    override fun getListTopicTugas(
        idKelas: Int,
        topic: String,
    ): Flow<Resource<GetListTopicTugasModel>> =
        object : NetworkOnlyResource<GetListTopicTugasModel, GetListTopicTugasResponse>() {
            override fun loadFromNetwork(data: GetListTopicTugasResponse): Flow<GetListTopicTugasModel> =
                GetListTopicTugasModel.mapResponseToModel(data)

            override suspend fun createCall(): Flow<ApiResponse<GetListTopicTugasResponse>> =
                remoteDataSource.getListTopicTugas(idKelas, topic)
        }.asFlow()

    override fun getDetailTugas(idTugas: Int): Flow<Resource<GetDetailTugasModel>> =
        object : NetworkOnlyResource<GetDetailTugasModel, GetDetailTugasResponse>() {
            override fun loadFromNetwork(data: GetDetailTugasResponse): Flow<GetDetailTugasModel> =
                GetDetailTugasModel.mapResponseToModel(data)

            override suspend fun createCall(): Flow<ApiResponse<GetDetailTugasResponse>> =
                remoteDataSource.getDetailTugas(idTugas)
        }.asFlow()

    override fun updateDetailTugas(
        payload: UpdateDetailTugasPayload,
        idTugas: Int,
    ): Flow<Resource<Boolean>> =
        object : NetworkOnlyResource<Boolean, UpdateDetailTugasResponse>() {
            override fun loadFromNetwork(data: UpdateDetailTugasResponse): Flow<Boolean> =
                flowOf(true)

            override suspend fun createCall(): Flow<ApiResponse<UpdateDetailTugasResponse>> =
                remoteDataSource.updateDetailTugas(payload, idTugas)
        }.asFlow()

    override fun getListTugasMahasiswa(
        idTugas: Int,
        page: Int?,
        limit: Int?
    ): Flow<Resource<GetListTugasMahasiswaModel>> =
        object : NetworkOnlyResource<GetListTugasMahasiswaModel, GetListTugasMahasiswaResponse>() {
            override fun loadFromNetwork(data: GetListTugasMahasiswaResponse): Flow<GetListTugasMahasiswaModel> =
                GetListTugasMahasiswaModel.mapResponseToModel(data)

            override suspend fun createCall(): Flow<ApiResponse<GetListTugasMahasiswaResponse>> =
                remoteDataSource.getListTugasMahasiswa(idTugas, page, limit)
        }.asFlow()

    override fun getJawabanForDosen(
        idTugas: Int,
        nim: String,
    ): Flow<Resource<GetJawabanForDosenModel>> =
        object : NetworkOnlyResource<GetJawabanForDosenModel, GetJawabanForDosenResponse>() {
            override fun loadFromNetwork(data: GetJawabanForDosenResponse): Flow<GetJawabanForDosenModel> =
                GetJawabanForDosenModel.mapResponseToModel(data)

            override suspend fun createCall(): Flow<ApiResponse<GetJawabanForDosenResponse>> =
                remoteDataSource.getJawabanForDosen(idTugas, nim)
        }.asFlow()

    override fun createNilai(payload: CreateNilaiPayload, idJawaban: Int): Flow<Resource<Boolean>> =
        object : NetworkOnlyResource<Boolean, CreateNilaiResponse>() {
            override fun loadFromNetwork(data: CreateNilaiResponse): Flow<Boolean> =
                flowOf(true)

            override suspend fun createCall(): Flow<ApiResponse<CreateNilaiResponse>> =
                remoteDataSource.createNilai(payload, idJawaban)
        }.asFlow()

    override fun getJawabanForMahasiswa(idTugas: Int): Flow<Resource<GetJawabanForMahasiswaModel>> =
        object :
            NetworkOnlyResource<GetJawabanForMahasiswaModel, GetJawabanForMahasiswaResponse>() {
            override fun loadFromNetwork(data: GetJawabanForMahasiswaResponse): Flow<GetJawabanForMahasiswaModel> =
                GetJawabanForMahasiswaModel.mapResponseToModel(data)

            override suspend fun createCall(): Flow<ApiResponse<GetJawabanForMahasiswaResponse>> =
                remoteDataSource.getJawabanForMahasiswa(idTugas)
        }.asFlow()

    override fun createJawaban(
        payload: CreateJawabanPayload,
        idTugas: Int
    ): Flow<Resource<Boolean>> =
        object :
            NetworkOnlyResource<Boolean, CreateJawabanResponse>() {
            override fun loadFromNetwork(data: CreateJawabanResponse): Flow<Boolean> =
                flowOf(true)

            override suspend fun createCall(): Flow<ApiResponse<CreateJawabanResponse>> =
                remoteDataSource.createJawaban(payload, idTugas)
        }.asFlow()

    override fun downloadNilai(idKelas: Int, query: String?): Flow<Resource<DownloadNilaiModel>> =
        object : NetworkOnlyResource<DownloadNilaiModel, DownloadNilaiResponse>() {
            override fun loadFromNetwork(data: DownloadNilaiResponse): Flow<DownloadNilaiModel> =
                DownloadNilaiModel.mapResponseToModel(data)

            override suspend fun createCall(): Flow<ApiResponse<DownloadNilaiResponse>> =
                remoteDataSource.downloadNilai(idKelas, query)
        }.asFlow()
}