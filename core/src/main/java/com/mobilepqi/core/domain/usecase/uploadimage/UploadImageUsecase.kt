package com.mobilepqi.core.domain.usecase.uploadimage

import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.domain.model.uploadimage.UploadImageModel
import java.io.File
import kotlinx.coroutines.flow.Flow

interface UploadImageUsecase {
    fun uploadImage(image: File): Flow<Resource<UploadImageModel>>
}