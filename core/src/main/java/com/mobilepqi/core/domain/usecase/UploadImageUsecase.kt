package com.mobilepqi.core.domain.usecase

import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.domain.model.UploadImageModel
import java.io.File
import kotlinx.coroutines.flow.Flow

interface UploadImageUsecase {
    fun uploadImage(image: File): Flow<Resource<UploadImageModel>>
}