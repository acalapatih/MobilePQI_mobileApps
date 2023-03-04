package com.mobilepqi.core.domain.model.menuqiroah

import com.mobilepqi.core.data.source.remote.response.menuqiroah.UpdateDetailMateriQiroahResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

data class UpdateDetailMateriQiroahModel(
    val description: String,
    val fileItem: List<FileItem>
) {
    data class FileItem(
        val url: String
    )
    companion object {
        fun mapResponseToModel(response: UpdateDetailMateriQiroahResponse): Flow<UpdateDetailMateriQiroahModel> {
            return flowOf(
                UpdateDetailMateriQiroahModel(
                    description = response.data?.description ?:"",
                    fileItem = response.data?.file?.map { fileItem ->
                        FileItem(
                            url = fileItem?.url ?:""
                        )
                    } ?: emptyList()
                )
            )
        }
    }
}