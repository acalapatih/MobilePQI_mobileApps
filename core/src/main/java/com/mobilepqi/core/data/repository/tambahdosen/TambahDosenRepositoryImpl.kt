package com.mobilepqi.core.data.repository.tambahdosen

import com.mobilepqi.core.data.NetworkOnlyResource
import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.data.source.remote.RemoteDataSource
import com.mobilepqi.core.data.source.remote.network.ApiResponse
import com.mobilepqi.core.data.source.remote.response.tambahdosen.TambahDosenResponse
import com.mobilepqi.core.domain.model.tambahdosen.TambahDosenModel
import com.mobilepqi.core.domain.repository.tambahdosen.TambahDosenRepository
import kotlinx.coroutines.flow.Flow

class TambahDosenRepositoryImpl(
    private val remoteDataSource: RemoteDataSource
): TambahDosenRepository {
    override fun tambahdosen(idKelas: Int): Flow<Resource<TambahDosenModel>> =
        object : NetworkOnlyResource<TambahDosenModel, TambahDosenResponse>() {
            override fun loadFromNetwork(data: TambahDosenResponse): Flow<TambahDosenModel> =
                TambahDosenModel.mapResponseToModel(data)
            override suspend fun createCall(): Flow<ApiResponse<TambahDosenResponse>> =
                remoteDataSource.tambahdosen(idKelas)
        }.asFlow()
}