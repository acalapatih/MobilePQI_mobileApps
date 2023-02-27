package com.mobilepqi.core.data.repository.menuqiroah

import com.mobilepqi.core.data.NetworkOnlyResource
import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.data.source.remote.RemoteDataSource
import com.mobilepqi.core.data.source.remote.network.ApiResponse
import com.mobilepqi.core.data.source.remote.response.menuqiroah.CreateMateriQiroahPayload
import com.mobilepqi.core.data.source.remote.response.menuqiroah.CreateMateriQiroahResponse
import com.mobilepqi.core.data.source.remote.response.menuqiroah.GetDetailMateriQiroahResponse
import com.mobilepqi.core.data.source.remote.response.menuqiroah.GetMateriQiroahResponse
import com.mobilepqi.core.domain.model.menuqiroah.CreateMateriQiroahModel
import com.mobilepqi.core.domain.model.menuqiroah.GetDetailMateriQiroahModel
import com.mobilepqi.core.domain.model.menuqiroah.GetMateriQiroahModel
import com.mobilepqi.core.domain.repository.menuqiroah.MenuQiroahRepository
import kotlinx.coroutines.flow.Flow

class MenuQiroahRepositoryImpl(
    private val remoteDataSource: RemoteDataSource
) : MenuQiroahRepository {
    override fun createMateriQiroah(
        request: CreateMateriQiroahPayload
    ): Flow<Resource<CreateMateriQiroahModel>> =
        object : NetworkOnlyResource<CreateMateriQiroahModel, CreateMateriQiroahResponse>() {
            override fun loadFromNetwork(data: CreateMateriQiroahResponse): Flow<CreateMateriQiroahModel> =
                CreateMateriQiroahModel.mapResponseToModel(data)

            override suspend fun createCall(): Flow<ApiResponse<CreateMateriQiroahResponse>> =
                remoteDataSource.createMateriQiroah(request)
        }.asFlow()

    override fun getMateriQiroah(): Flow<Resource<GetMateriQiroahModel>> =
        object : NetworkOnlyResource<GetMateriQiroahModel, GetMateriQiroahResponse>() {
            override fun loadFromNetwork(data: GetMateriQiroahResponse): Flow<GetMateriQiroahModel> =
                GetMateriQiroahModel.mapResponseToModel(data)

            override suspend fun createCall(): Flow<ApiResponse<GetMateriQiroahResponse>> =
                remoteDataSource.getMateriQiroah()
        }.asFlow()

    override fun getDetailMateriQiroah(id: Int): Flow<Resource<GetDetailMateriQiroahModel>> =
        object : NetworkOnlyResource<GetDetailMateriQiroahModel, GetDetailMateriQiroahResponse>() {
            override fun loadFromNetwork(data: GetDetailMateriQiroahResponse): Flow<GetDetailMateriQiroahModel> =
                GetDetailMateriQiroahModel.mapResponseToModel(data)

            override suspend fun createCall(): Flow<ApiResponse<GetDetailMateriQiroahResponse>> =
                remoteDataSource.getDetailMateriQiroah(id)
        }.asFlow()
}