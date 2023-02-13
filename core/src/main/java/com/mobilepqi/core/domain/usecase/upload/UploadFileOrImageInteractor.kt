package com.mobilepqi.core.domain.usecase.upload

import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.domain.model.upload.UploadModel
import com.mobilepqi.core.domain.repository.upload.UploadFileOrImageRepository
import java.io.File
import kotlinx.coroutines.flow.Flow
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody

class UploadFileOrImageInteractor(
    private val repository: UploadFileOrImageRepository
) : UploadFileOrImageUsecase {
    override fun uploadFileOrImage(key: String, type: String, file: File): Flow<Resource<UploadModel>> {
        val fileType = if (type == "image") "image/*" else "file/*"
        val reqFile: RequestBody = file.asRequestBody(fileType.toMediaTypeOrNull())
        val imageMultipart: MultipartBody.Part = MultipartBody.Part.createFormData(
            key,
            file.name,
            reqFile
        )
        return repository.uploadFileOrImage(imageMultipart)
    }
}