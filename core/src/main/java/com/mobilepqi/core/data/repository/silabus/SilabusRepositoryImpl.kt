package com.mobilepqi.core.data.repository.silabus

import com.mobilepqi.core.data.NetworkOnlyResource
import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.data.source.remote.RemoteDataSource
import com.mobilepqi.core.data.source.remote.network.ApiResponse
import com.mobilepqi.core.data.source.remote.response.silabus.CreateSilabusPayload
import com.mobilepqi.core.data.source.remote.response.silabus.CreateSilabusResponse
import com.mobilepqi.core.data.source.remote.response.silabus.DeleteSilabusResponse
import com.mobilepqi.core.data.source.remote.response.silabus.GetSilabusResponse
import com.mobilepqi.core.domain.model.silabus.GetSilabusModel
import com.mobilepqi.core.domain.repository.silabus.SilabusRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class SilabusRepositoryImpl(
    private val remoteDataSource: RemoteDataSource
) : SilabusRepository {
    override fun createSilabus(
        request: CreateSilabusPayload,
        idKelas: Int
    ): Flow<Resource<Boolean>> =
        object : NetworkOnlyResource<Boolean, CreateSilabusResponse>() {
            override fun loadFromNetwork(data: CreateSilabusResponse): Flow<Boolean> =
                flowOf(true)

            override suspend fun createCall(): Flow<ApiResponse<CreateSilabusResponse>> =
                remoteDataSource.createSilabus(request, idKelas)
        }.asFlow()

    override fun getSilabus(idKelas: Int): Flow<Resource<GetSilabusModel>> =
        object : NetworkOnlyResource<GetSilabusModel, GetSilabusResponse>() {
            override fun loadFromNetwork(data: GetSilabusResponse): Flow<GetSilabusModel> =
                GetSilabusModel.mapResponseToModel(data)

            override suspend fun createCall(): Flow<ApiResponse<GetSilabusResponse>> =
                remoteDataSource.getSilabus(idKelas)
        }.asFlow()

    override fun deleteSilabus(idKelas: Int): Flow<Resource<Boolean>> =
        object : NetworkOnlyResource<Boolean, DeleteSilabusResponse>() {
            override fun loadFromNetwork(data: DeleteSilabusResponse): Flow<Boolean> =
                flowOf(true)

            override suspend fun createCall(): Flow<ApiResponse<DeleteSilabusResponse>> =
                remoteDataSource.deleteSilabus(idKelas)
        }.asFlow()
}