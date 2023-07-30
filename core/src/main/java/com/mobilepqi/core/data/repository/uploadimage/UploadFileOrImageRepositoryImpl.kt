package com.mobilepqi.core.data.repository.uploadimage

import com.mobilepqi.core.data.NetworkOnlyResource
import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.data.source.remote.RemoteDataSource
import com.mobilepqi.core.data.source.remote.network.ApiResponse
import com.mobilepqi.core.data.source.remote.response.uploadimage.UploadResponse
import com.mobilepqi.core.domain.model.upload.UploadModel
import com.mobilepqi.core.domain.repository.upload.UploadFileOrImageRepository
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody

class UploadFileOrImageRepositoryImpl(
    private val remoteDataSource: RemoteDataSource
) : UploadFileOrImageRepository {
    override fun uploadFileOrImage(file: MultipartBody.Part): Flow<Resource<UploadModel>> =
        object : NetworkOnlyResource<UploadModel, UploadResponse>() {
            override fun loadFromNetwork(data: UploadResponse): Flow<UploadModel> =
                UploadModel.mapResponseToModel(data)

            override suspend fun createCall(): Flow<ApiResponse<UploadResponse>> =
                remoteDataSource.uploadFileOrImage(file)

        }.asFlow()
}