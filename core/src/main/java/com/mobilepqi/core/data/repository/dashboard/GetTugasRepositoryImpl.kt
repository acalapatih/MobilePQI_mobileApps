package com.mobilepqi.core.data.repository.dashboard

import com.mobilepqi.core.data.NetworkOnlyResource
import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.data.source.remote.RemoteDataSource
import com.mobilepqi.core.data.source.remote.network.ApiResponse
import com.mobilepqi.core.data.source.remote.response.dashboard.GetTugasResponse
import com.mobilepqi.core.domain.model.dashboard.GetTugasModel
import com.mobilepqi.core.domain.repository.dashboard.GetTugasRepository
import kotlinx.coroutines.flow.Flow

class GetTugasRepositoryImpl(
    private val remoteDataSource: RemoteDataSource
): GetTugasRepository {
    override fun getTugas(idKelas: Int): Flow<Resource<GetTugasModel>> =
        object : NetworkOnlyResource<GetTugasModel, GetTugasResponse>() {
            override fun loadFromNetwork(data: GetTugasResponse): Flow<GetTugasModel> =
                GetTugasModel.mapResponseToModel(data)

            override suspend fun createCall(): Flow<ApiResponse<GetTugasResponse>> =
                remoteDataSource.getTugas(idKelas)

        }.asFlow()

}