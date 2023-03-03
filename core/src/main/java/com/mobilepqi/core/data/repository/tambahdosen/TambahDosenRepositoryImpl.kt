package com.mobilepqi.core.data.repository.tambahdosen

import com.mobilepqi.core.data.NetworkOnlyResource
import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.data.source.remote.RemoteDataSource
import com.mobilepqi.core.data.source.remote.network.ApiResponse
import com.mobilepqi.core.data.source.remote.response.tambahdosen.GetTambahDosenResponse
import com.mobilepqi.core.data.source.remote.response.tambahdosen.PostTambahDosenPayload
import com.mobilepqi.core.data.source.remote.response.tambahdosen.PostTambahDosenResponse
import com.mobilepqi.core.domain.model.tambahdosen.GetTambahDosenModel
import com.mobilepqi.core.domain.model.tambahdosen.PostTambahDosenModel
import com.mobilepqi.core.domain.repository.tambahdosen.TambahDosenRepository
import kotlinx.coroutines.flow.Flow

class TambahDosenRepositoryImpl(
    private val remoteDataSource: RemoteDataSource
): TambahDosenRepository {
    override fun getTambahDosen(idKelas: Int): Flow<Resource<GetTambahDosenModel>> =
        object : NetworkOnlyResource<GetTambahDosenModel, GetTambahDosenResponse>() {
            override fun loadFromNetwork(data: GetTambahDosenResponse): Flow<GetTambahDosenModel> =
                GetTambahDosenModel.mapResponseToModel(data)
            override suspend fun createCall(): Flow<ApiResponse<GetTambahDosenResponse>> =
                remoteDataSource.getTambahDosen(idKelas)
        }.asFlow()

    override fun postTambahDosen(request: PostTambahDosenPayload, idKelas: Int): Flow<Resource<PostTambahDosenModel>> =
        object : NetworkOnlyResource<PostTambahDosenModel, PostTambahDosenResponse>() {
            override fun loadFromNetwork(data: PostTambahDosenResponse): Flow<PostTambahDosenModel> =
                PostTambahDosenModel.mapResponseToModel(data)
            override suspend fun createCall(): Flow<ApiResponse<PostTambahDosenResponse>> =
                remoteDataSource.postTambahDosen(request, idKelas)
        }.asFlow()
}