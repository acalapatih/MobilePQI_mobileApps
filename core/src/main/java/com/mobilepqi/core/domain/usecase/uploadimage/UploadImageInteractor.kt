package com.mobilepqi.core.domain.usecase.uploadimage

import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.domain.model.uploadimage.UploadImageModel
import com.mobilepqi.core.domain.repository.uploadimage.UploadImageRepository
import java.io.File
import kotlinx.coroutines.flow.Flow
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody

class UploadImageInteractor(
    private val repository: UploadImageRepository
) : UploadImageUsecase {
    override fun uploadImage(image: File): Flow<Resource<UploadImageModel>> {
        val reqFile: RequestBody = image.asRequestBody("image/*".toMediaTypeOrNull())
        val imageMultipart: MultipartBody.Part = MultipartBody.Part.createFormData(
            "avatar",
            image.name,
            reqFile
        )
        return repository.uploadImage(imageMultipart)
    }
}