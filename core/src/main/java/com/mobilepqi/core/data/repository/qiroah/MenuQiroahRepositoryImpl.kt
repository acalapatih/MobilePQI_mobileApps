package com.mobilepqi.core.data.repository.qiroah

import com.mobilepqi.core.data.NetworkOnlyResource
import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.data.source.remote.RemoteDataSource
import com.mobilepqi.core.data.source.remote.network.ApiResponse
import com.mobilepqi.core.data.source.remote.response.qiroah.*
import com.mobilepqi.core.domain.model.menuqiroah.*
import com.mobilepqi.core.domain.repository.qiroah.MenuQiroahRepository
import kotlinx.coroutines.flow.Flow

class MenuQiroahRepositoryImpl(
    private val remoteDataSource: RemoteDataSource
) : MenuQiroahRepository {
    override fun createMateriQiroah(
        request: CreateMateriQiroahPayload,
        idKelas: Int
    ): Flow<Resource<CreateMateriQiroahModel>> =
        object : NetworkOnlyResource<CreateMateriQiroahModel, CreateMateriQiroahResponse>() {
            override fun loadFromNetwork(data: CreateMateriQiroahResponse): Flow<CreateMateriQiroahModel> =
                CreateMateriQiroahModel.mapResponseToModel(data)

            override suspend fun createCall(): Flow<ApiResponse<CreateMateriQiroahResponse>> =
                remoteDataSource.createMateriQiroah(request, idKelas)
        }.asFlow()

    override fun getMateriQiroah(idKelas: Int): Flow<Resource<GetMateriQiroahModel>> =
        object : NetworkOnlyResource<GetMateriQiroahModel, GetMateriQiroahResponse>() {
            override fun loadFromNetwork(data: GetMateriQiroahResponse): Flow<GetMateriQiroahModel> =
                GetMateriQiroahModel.mapResponseToModel(data)

            override suspend fun createCall(): Flow<ApiResponse<GetMateriQiroahResponse>> =
                remoteDataSource.getMateriQiroah(idKelas)
        }.asFlow()

    override fun getDetailMateriQiroah(id: Int): Flow<Resource<GetDetailMateriQiroahModel>> =
        object : NetworkOnlyResource<GetDetailMateriQiroahModel, GetDetailMateriQiroahResponse>() {
            override fun loadFromNetwork(data: GetDetailMateriQiroahResponse): Flow<GetDetailMateriQiroahModel> =
                GetDetailMateriQiroahModel.mapResponseToModel(data)

            override suspend fun createCall(): Flow<ApiResponse<GetDetailMateriQiroahResponse>> =
                remoteDataSource.getDetailMateriQiroah(id)
        }.asFlow()

    override fun deleteMateriQiroah(idMateri: Int): Flow<Resource<DeleteMateriQiroahModel>> =
        object : NetworkOnlyResource<DeleteMateriQiroahModel, DeleteMateriQiroahResponse>() {
            override fun loadFromNetwork(data: DeleteMateriQiroahResponse): Flow<DeleteMateriQiroahModel> =
                DeleteMateriQiroahModel.mapResponseToModel(data)

            override suspend fun createCall(): Flow<ApiResponse<DeleteMateriQiroahResponse>> =
                remoteDataSource.deleteMateriQiroah(idMateri)
        }.asFlow()

    override fun updateDetailMateriQiroah(
        request: UpdateDetailMateriQiroahPayload,
        idMateri: Int
    ): Flow<Resource<UpdateDetailMateriQiroahModel>> =
        object : NetworkOnlyResource<UpdateDetailMateriQiroahModel, UpdateDetailMateriQiroahResponse>() {
            override fun loadFromNetwork(data: UpdateDetailMateriQiroahResponse): Flow<UpdateDetailMateriQiroahModel> =
                UpdateDetailMateriQiroahModel.mapResponseToModel(data)

            override suspend fun createCall(): Flow<ApiResponse<UpdateDetailMateriQiroahResponse>> =
                remoteDataSource.updateDetailMateriQiroah(request, idMateri)
        }.asFlow()
}