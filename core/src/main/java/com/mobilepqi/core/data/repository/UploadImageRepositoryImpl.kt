package com.mobilepqi.core.data.repository

import com.mobilepqi.core.data.NetworkOnlyResource
import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.data.source.remote.RemoteDataSource
import com.mobilepqi.core.data.source.remote.network.ApiResponse
import com.mobilepqi.core.data.source.remote.response.UploadImageResponse
import com.mobilepqi.core.domain.model.UploadImageModel
import com.mobilepqi.core.domain.repository.UploadImageRepository
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody

class UploadImageRepositoryImpl(
    private val remoteDataSource: RemoteDataSource
) : UploadImageRepository {
    override fun uploadImage(file: MultipartBody.Part): Flow<Resource<UploadImageModel>> =
        object : NetworkOnlyResource<UploadImageModel, UploadImageResponse>() {
            override fun loadFromNetwork(data: UploadImageResponse): Flow<UploadImageModel> =
                UploadImageModel.mapResponseToModel(data)

            override suspend fun createCall(): Flow<ApiResponse<UploadImageResponse>> =
                remoteDataSource.uploadImage(file)

        }.asFlow()
}