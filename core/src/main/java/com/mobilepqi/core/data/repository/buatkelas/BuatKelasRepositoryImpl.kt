package com.mobilepqi.core.data.repository.buatkelas

import com.mobilepqi.core.data.NetworkOnlyResource
import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.data.source.remote.RemoteDataSource
import com.mobilepqi.core.data.source.remote.network.ApiResponse
import com.mobilepqi.core.data.source.remote.response.buatkelas.BuatKelasPayload
import com.mobilepqi.core.data.source.remote.response.buatkelas.BuatKelasResponse
import com.mobilepqi.core.domain.model.buatkelas.BuatKelasModel
import com.mobilepqi.core.domain.repository.buatkelas.BuatKelasRepository
import kotlinx.coroutines.flow.Flow

class BuatKelasRepositoryImpl(
    private val remoteDataSource: RemoteDataSource
): BuatKelasRepository {
    override fun buatkelas(request: BuatKelasPayload): Flow<Resource<BuatKelasModel>> =
        object : NetworkOnlyResource<BuatKelasModel, BuatKelasResponse>() {
            override fun loadFromNetwork(data: BuatKelasResponse): Flow<BuatKelasModel> =
                BuatKelasModel.mapResponseToModel(data)

            override suspend fun createCall(): Flow<ApiResponse<BuatKelasResponse>> =
                remoteDataSource.buatkelas(request)

        }.asFlow()
}