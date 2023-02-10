package com.mobilepqi.core.domain.repository.uploadimage

import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.domain.model.uploadimage.UploadImageModel
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody

interface UploadImageRepository {
    fun uploadImage(file: MultipartBody.Part): Flow<Resource<UploadImageModel>>
}