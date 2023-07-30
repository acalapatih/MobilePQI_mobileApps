package com.mobilepqi.core.domain.usecase.upload

import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.domain.model.upload.UploadModel
import java.io.File
import kotlinx.coroutines.flow.Flow

interface UploadFileOrImageUsecase {
    fun uploadFileOrImage(key: String, type: String, file: File): Flow<Resource<UploadModel>>
}