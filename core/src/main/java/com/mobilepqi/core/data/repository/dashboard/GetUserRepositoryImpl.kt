package com.mobilepqi.core.data.repository.dashboard

import com.mobilepqi.core.data.NetworkOnlyResource
import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.data.source.remote.RemoteDataSource
import com.mobilepqi.core.data.source.remote.network.ApiResponse
import com.mobilepqi.core.data.source.remote.response.dashboard.GetUserResponse
import com.mobilepqi.core.domain.model.dashboard.GetUserModel
import com.mobilepqi.core.domain.repository.dashboard.GetUserRepository
import kotlinx.coroutines.flow.Flow

class GetUserRepositoryImpl(
    private val remoteDataSource: RemoteDataSource
): GetUserRepository {
    override fun getUser(): Flow<Resource<GetUserModel>> =
        object : NetworkOnlyResource<GetUserModel, GetUserResponse>() {
            override fun loadFromNetwork(data: GetUserResponse): Flow<GetUserModel> =
                GetUserModel.mapResponseToModel(data)

            override suspend fun createCall(): Flow<ApiResponse<GetUserResponse>> =
                remoteDataSource.getUser()
        }.asFlow()
}