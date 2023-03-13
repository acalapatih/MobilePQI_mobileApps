package com.mobilepqi.core.data.repository.dashboard

import com.mobilepqi.core.data.NetworkOnlyResource
import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.data.source.remote.RemoteDataSource
import com.mobilepqi.core.data.source.remote.network.ApiResponse
import com.mobilepqi.core.data.source.remote.response.dashboard.GetClassResponse
import com.mobilepqi.core.domain.model.dashboard.GetClassModel
import com.mobilepqi.core.domain.repository.dashboard.GetClassRepository
import kotlinx.coroutines.flow.Flow

class GetClassRepositoryImpl(
    private val remoteDataSource: RemoteDataSource
): GetClassRepository {
    override fun getClass(idKelas: Int): Flow<Resource<GetClassModel>> =
        object : NetworkOnlyResource<GetClassModel, GetClassResponse>() {
            override fun loadFromNetwork(data: GetClassResponse): Flow<GetClassModel> =
                GetClassModel.mapResponseToModel(data)

            override suspend fun createCall(): Flow<ApiResponse<GetClassResponse>> =
                remoteDataSource.getClass(idKelas)
        }.asFlow()
}