package com.mobilepqi.core.data.repository.profil

import com.mobilepqi.core.data.NetworkOnlyResource
import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.data.source.remote.RemoteDataSource
import com.mobilepqi.core.data.source.remote.network.ApiResponse
import com.mobilepqi.core.data.source.remote.response.profil.ProfilResponse
import com.mobilepqi.core.domain.model.profil.ProfilModel
import com.mobilepqi.core.domain.repository.profil.ProfilRepository
import kotlinx.coroutines.flow.Flow

class ProfilRepositoryImpl(
    private val remoteDataSource: RemoteDataSource
): ProfilRepository {
    override fun profil(): Flow<Resource<ProfilModel>> =
        object : NetworkOnlyResource<ProfilModel, ProfilResponse>() {
            override fun loadFromNetwork(data: ProfilResponse): Flow<ProfilModel> =
                ProfilModel.mapResponseToModel(data)

            override suspend fun createCall(): Flow<ApiResponse<ProfilResponse>> =
                remoteDataSource.profil()
        }.asFlow()
}