package com.mobilepqi.core.data.repository.ibadah

import com.mobilepqi.core.data.NetworkOnlyResource
import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.data.source.remote.RemoteDataSource
import com.mobilepqi.core.data.source.remote.network.ApiResponse
import com.mobilepqi.core.data.source.remote.response.ibadah.*
import com.mobilepqi.core.domain.model.menuibadah.*
import com.mobilepqi.core.domain.repository.ibadah.MenuIbadahRepository
import kotlinx.coroutines.flow.Flow

class MenuIbadahRepositoryImpl(
    private val remoteDataSource: RemoteDataSource
) : MenuIbadahRepository {
    override fun createMateriIbadah(
        request: CreateMateriIbadahPayload,
        idKelas: Int
    ): Flow<Resource<CreateMateriIbadahModel>> =
        object : NetworkOnlyResource<CreateMateriIbadahModel, CreateMateriIbadahResponse>() {
            override fun loadFromNetwork(data: CreateMateriIbadahResponse): Flow<CreateMateriIbadahModel> =
                CreateMateriIbadahModel.mapResponseToModel(data)

            override suspend fun createCall(): Flow<ApiResponse<CreateMateriIbadahResponse>> =
                remoteDataSource.createMateriIbadah(request, idKelas)
        }.asFlow()

    override fun getMateriIbadah(idKelas: Int): Flow<Resource<GetMateriIbadahModel>> =
        object : NetworkOnlyResource<GetMateriIbadahModel, GetMateriIbadahResponse>() {
            override fun loadFromNetwork(data: GetMateriIbadahResponse): Flow<GetMateriIbadahModel> =
                GetMateriIbadahModel.mapResponseToModel(data)

            override suspend fun createCall(): Flow<ApiResponse<GetMateriIbadahResponse>> =
                remoteDataSource.getMateriIbadah(idKelas)
        }.asFlow()

    override fun getDetailMateriIbadah(id: Int): Flow<Resource<GetDetailMateriIbadahModel>> =
        object : NetworkOnlyResource<GetDetailMateriIbadahModel, GetDetailMateriIbadahResponse>() {
            override fun loadFromNetwork(data: GetDetailMateriIbadahResponse): Flow<GetDetailMateriIbadahModel> =
                GetDetailMateriIbadahModel.mapResponseToModel(data)

            override suspend fun createCall(): Flow<ApiResponse<GetDetailMateriIbadahResponse>> =
                remoteDataSource.getDetailMateriIbadah(id)
        }.asFlow()

    override fun deleteMateriIbadah(idMateri: Int): Flow<Resource<DeleteMateriIbadahModel>> =
        object : NetworkOnlyResource<DeleteMateriIbadahModel, DeleteMateriIbadahResponse>() {
            override fun loadFromNetwork(data: DeleteMateriIbadahResponse): Flow<DeleteMateriIbadahModel> =
                DeleteMateriIbadahModel.mapResponseToModel(data)

            override suspend fun createCall(): Flow<ApiResponse<DeleteMateriIbadahResponse>> =
                remoteDataSource.deleteMateriIbadah(idMateri)
        }.asFlow()

    override fun updateDetailMateriIbadah(
        request: UpdateDetailMateriIbadahPayload,
        idMateri: Int
    ): Flow<Resource<UpdateDetailMateriIbadahModel>> =
        object : NetworkOnlyResource<UpdateDetailMateriIbadahModel, UpdateDetailMateriIbadahResponse>() {
            override fun loadFromNetwork(data: UpdateDetailMateriIbadahResponse): Flow<UpdateDetailMateriIbadahModel> =
                UpdateDetailMateriIbadahModel.mapResponseToModel(data)

            override suspend fun createCall(): Flow<ApiResponse<UpdateDetailMateriIbadahResponse>> =
                remoteDataSource.updateDetailMateriIbadah(request, idMateri)
        }.asFlow()
}