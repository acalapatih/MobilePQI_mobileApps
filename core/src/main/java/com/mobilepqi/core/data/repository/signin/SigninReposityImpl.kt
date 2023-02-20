package com.mobilepqi.core.data.repository.signin

import com.mobilepqi.core.data.NetworkOnlyResource
import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.data.source.local.LocalDataSource
import com.mobilepqi.core.data.source.remote.RemoteDataSource
import com.mobilepqi.core.data.source.remote.network.ApiResponse
import com.mobilepqi.core.data.source.remote.response.signin.SigninPayload
import com.mobilepqi.core.data.source.remote.response.signin.SigninResponse
import com.mobilepqi.core.domain.model.signin.SigninModel
import com.mobilepqi.core.domain.repository.signin.SigninRepository
import kotlinx.coroutines.flow.Flow

class SigninReposityImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : SigninRepository {
    override fun login(request: SigninPayload): Flow<Resource<SigninModel>> =
        object : NetworkOnlyResource<SigninModel, SigninResponse>() {
            override fun loadFromNetwork(data: SigninResponse): Flow<SigninModel> =
                SigninModel.mapResponseToModel(data)

            override suspend fun createCall(): Flow<ApiResponse<SigninResponse>> =
                remoteDataSource.login(request)

        }.asFlow()

    override fun setToken(token: String) = localDataSource.setToken(token)
}