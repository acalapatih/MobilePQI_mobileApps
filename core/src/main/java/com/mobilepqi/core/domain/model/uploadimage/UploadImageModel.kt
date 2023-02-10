package com.mobilepqi.core.domain.model.uploadimage

import com.mobilepqi.core.data.source.remote.response.uploadimage.UploadImageResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

data class UploadImageModel(
    val imageUrl: String
) {
    companion object {
        fun mapResponseToModel(response: UploadImageResponse): Flow<UploadImageModel> {
            return flowOf(
                UploadImageModel(
                    imageUrl = response.data?.avatar ?: ""
                )
            )
        }
    }
}
