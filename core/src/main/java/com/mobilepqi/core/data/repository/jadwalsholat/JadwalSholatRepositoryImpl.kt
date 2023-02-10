package com.mobilepqi.core.data.repository.jadwalsholat

import com.mobilepqi.core.data.NetworkOnlyResource
import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.data.source.remote.RemoteDataSource
import com.mobilepqi.core.data.source.remote.network.ApiResponse
import com.mobilepqi.core.data.source.remote.response.jadwalsholat.JadwalSholatResponse
import com.mobilepqi.core.domain.model.jadwalsholat.JadwalSholatModel
import com.mobilepqi.core.domain.repository.jadwalsholat.JadwalSholatRepository
import kotlinx.coroutines.flow.Flow

class JadwalSholatRepositoryImpl(
    private val remoteDataSource: RemoteDataSource
) : JadwalSholatRepository {

    override fun getJadwalSholat(
        latitude: String,
        longitude: String
    ): Flow<Resource<JadwalSholatModel>> =
        object : NetworkOnlyResource<JadwalSholatModel, JadwalSholatResponse>() {
            override fun loadFromNetwork(data: JadwalSholatResponse): Flow<JadwalSholatModel> =
                JadwalSholatModel.mapResponseToModel(data)

            override suspend fun createCall(): Flow<ApiResponse<JadwalSholatResponse>> =
                remoteDataSource.getJadwalSholat(latitude, longitude)

        }.asFlow()
}