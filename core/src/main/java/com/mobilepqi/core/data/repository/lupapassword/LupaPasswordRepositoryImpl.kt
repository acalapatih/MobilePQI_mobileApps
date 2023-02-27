package com.mobilepqi.core.data.repository.lupapassword

import com.mobilepqi.core.data.NetworkOnlyResource
import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.data.source.remote.RemoteDataSource
import com.mobilepqi.core.data.source.remote.network.ApiResponse
import com.mobilepqi.core.data.source.remote.response.lupapassword.LupaPasswordPayload
import com.mobilepqi.core.data.source.remote.response.lupapassword.LupaPasswordResponse
import com.mobilepqi.core.domain.repository.lupapassword.LupaPasswordRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class LupaPasswordRepositoryImpl(
    private val remoteDataSource: RemoteDataSource
) : LupaPasswordRepository {
    override fun lupaPassword(request: LupaPasswordPayload): Flow<Resource<Boolean>> =
        object : NetworkOnlyResource<Boolean, LupaPasswordResponse>() {
            override fun loadFromNetwork(data: LupaPasswordResponse): Flow<Boolean> =
                flowOf(true)

            override suspend fun createCall(): Flow<ApiResponse<LupaPasswordResponse>> =
                remoteDataSource.lupaPassword(request)
        }.asFlow()
}