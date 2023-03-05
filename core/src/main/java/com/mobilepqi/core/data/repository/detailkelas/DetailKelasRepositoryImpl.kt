package com.mobilepqi.core.data.repository.detailkelas

import com.mobilepqi.core.data.NetworkOnlyResource
import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.data.source.remote.RemoteDataSource
import com.mobilepqi.core.data.source.remote.network.ApiResponse
import com.mobilepqi.core.data.source.remote.response.detailkelas.DetailKelasResponse
import com.mobilepqi.core.domain.model.detailkelas.DetailKelasModel
import com.mobilepqi.core.domain.repository.detailkelas.DetailKelasRepository
import kotlinx.coroutines.flow.Flow

class DetailKelasRepositoryImpl(
    private val remoteDataSource: RemoteDataSource
): DetailKelasRepository {
    override fun detailkelas(idKelas: Int): Flow<Resource<DetailKelasModel>> =
        object : NetworkOnlyResource<DetailKelasModel, DetailKelasResponse>() {
            override fun loadFromNetwork(data: DetailKelasResponse): Flow<DetailKelasModel> =
                DetailKelasModel.mapResponseToModel(data)
            override suspend fun createCall(): Flow<ApiResponse<DetailKelasResponse>> =
                remoteDataSource.detailkelas(idKelas)
        }.asFlow()
}