package com.mobilepqi.core.domain.repository.upload

import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.domain.model.upload.UploadModel
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody

interface UploadFileOrImageRepository {
    fun uploadFileOrImage(file: MultipartBody.Part): Flow<Resource<UploadModel>>
}