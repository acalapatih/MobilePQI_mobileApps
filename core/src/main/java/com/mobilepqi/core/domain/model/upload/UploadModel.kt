package com.mobilepqi.core.domain.model.upload

import com.mobilepqi.core.data.source.remote.response.uploadimage.UploadResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

data class UploadModel(
    val fileUrl: String
) {
    companion object {
        fun mapResponseToModel(response: UploadResponse): Flow<UploadModel> {
            return flowOf(
                UploadModel(
                    fileUrl = response.data?.url ?: ""
                )
            )
        }
    }
}
