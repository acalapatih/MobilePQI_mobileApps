package com.mobilepqi.core.data.repository.signup

import com.mobilepqi.core.data.NetworkOnlyResource
import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.data.source.remote.RemoteDataSource
import com.mobilepqi.core.data.source.remote.network.ApiResponse
import com.mobilepqi.core.data.source.remote.response.signup.SignupPayload
import com.mobilepqi.core.data.source.remote.response.signup.SignupResponse
import com.mobilepqi.core.domain.repository.signup.SignupRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class SignupRepositoryImpl(
    private val remoteDataSource: RemoteDataSource
): SignupRepository {
    override fun signup(request: SignupPayload): Flow<Resource<Boolean>> =
        object : NetworkOnlyResource<Boolean, SignupResponse>() {
            override fun loadFromNetwork(data: SignupResponse): Flow<Boolean> =
                flowOf(true)
            override suspend fun createCall(): Flow<ApiResponse<SignupResponse>> =
                remoteDataSource.signup(request)
        }.asFlow()
}