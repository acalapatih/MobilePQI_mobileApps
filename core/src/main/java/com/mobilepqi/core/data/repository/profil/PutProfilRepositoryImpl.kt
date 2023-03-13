package com.mobilepqi.core.data.repository.profil

import com.mobilepqi.core.data.NetworkOnlyResource
import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.data.source.remote.RemoteDataSource
import com.mobilepqi.core.data.source.remote.network.ApiResponse
import com.mobilepqi.core.data.source.remote.response.profil.PutProfilPayload
import com.mobilepqi.core.data.source.remote.response.profil.PutProfilResponse
import com.mobilepqi.core.domain.model.profil.PutProfilModel
import com.mobilepqi.core.domain.repository.profil.PutProfilRepository
import kotlinx.coroutines.flow.Flow

class PutProfilRepositoryImpl(
    private val remoteDataSource: RemoteDataSource
) : PutProfilRepository {
    override fun putprofil(request: PutProfilPayload): Flow<Resource<PutProfilModel>> =
        object : NetworkOnlyResource<PutProfilModel, PutProfilResponse>() {
            override fun loadFromNetwork(data: PutProfilResponse): Flow<PutProfilModel> =
                PutProfilModel.mapResponseToModel(data)

            override suspend fun createCall(): Flow<ApiResponse<PutProfilResponse>> =
                remoteDataSource.putprofil(request)

        }.asFlow()
}