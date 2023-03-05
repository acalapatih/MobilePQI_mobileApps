package com.mobilepqi.core.data.repository.daftarkelas

import com.mobilepqi.core.data.NetworkOnlyResource
import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.data.source.remote.RemoteDataSource
import com.mobilepqi.core.data.source.remote.network.ApiResponse
import com.mobilepqi.core.data.source.remote.response.daftarkelas.DaftarKelasResponse
import com.mobilepqi.core.domain.model.daftarkelas.DaftarKelasModel
import com.mobilepqi.core.domain.repository.daftarkelas.DaftarKelasRepository
import kotlinx.coroutines.flow.Flow

class DaftarKelasRepositoryImpl(
    private val remoteDataSource: RemoteDataSource
): DaftarKelasRepository {
    override fun daftarkelas(): Flow<Resource<DaftarKelasModel>> =
        object : NetworkOnlyResource<DaftarKelasModel, DaftarKelasResponse>() {
            override fun loadFromNetwork(data: DaftarKelasResponse): Flow<DaftarKelasModel> =
                DaftarKelasModel.mapResponseToModel(data)
            override suspend fun createCall(): Flow<ApiResponse<DaftarKelasResponse>> =
                remoteDataSource.daftarkelas()
        }.asFlow()
}