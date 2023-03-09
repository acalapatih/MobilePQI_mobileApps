package com.mobilepqi.core.data.repository.tugas

import com.mobilepqi.core.data.NetworkOnlyResource
import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.data.source.remote.RemoteDataSource
import com.mobilepqi.core.data.source.remote.network.ApiResponse
import com.mobilepqi.core.data.source.remote.response.tugas.CreateTugasPayload
import com.mobilepqi.core.data.source.remote.response.tugas.CreateTugasResponse
import com.mobilepqi.core.data.source.remote.response.tugas.GetListTugasResponse
import com.mobilepqi.core.domain.model.tugas.GetListTugasModel
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
}