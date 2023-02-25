package com.mobilepqi.core.data.repository.menuqiroah

import com.mobilepqi.core.data.NetworkOnlyResource
import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.data.source.remote.RemoteDataSource
import com.mobilepqi.core.data.source.remote.network.ApiResponse
import com.mobilepqi.core.data.source.remote.response.menuqiroah.CreateMateriQiroahPayload
import com.mobilepqi.core.data.source.remote.response.menuqiroah.CreateMateriQiroahResponse
import com.mobilepqi.core.data.source.remote.response.menuqiroah.GetMateriQiroahResponse
import com.mobilepqi.core.domain.model.menuqiroah.GetMateriQiroahModel
import com.mobilepqi.core.domain.model.menuqiroah.MenuQiroahModel
import com.mobilepqi.core.domain.repository.menuqiroah.MenuQiroahRepository
import kotlinx.coroutines.flow.Flow

class MenuQiroahRepositoryImpl(
    private val remoteDataSource: RemoteDataSource
) : MenuQiroahRepository {
    override fun createMateriQiroah(
        request: CreateMateriQiroahPayload
    ): Flow<Resource<MenuQiroahModel>> =
        object : NetworkOnlyResource<MenuQiroahModel, CreateMateriQiroahResponse>() {
            override fun loadFromNetwork(data: CreateMateriQiroahResponse): Flow<MenuQiroahModel> =
                MenuQiroahModel.mapResponseToModel(data)

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
}