package com.mobilepqi.core.domain.model.menuibadah

import com.mobilepqi.core.data.source.remote.response.ibadah.UpdateDetailMateriIbadahResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

data class UpdateDetailMateriIbadahModel(
    val description: String,
    val fileItem: List<FileItem>
) {
    data class FileItem(
        val url: String
    )
    companion object {
        fun mapResponseToModel(response: UpdateDetailMateriIbadahResponse): Flow<UpdateDetailMateriIbadahModel> {
            return flowOf(
                UpdateDetailMateriIbadahModel(
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