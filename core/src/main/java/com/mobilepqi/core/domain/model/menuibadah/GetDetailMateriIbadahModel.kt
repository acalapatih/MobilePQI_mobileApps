package com.mobilepqi.core.domain.model.menuibadah

import com.mobilepqi.core.data.source.remote.response.ibadah.GetDetailMateriIbadahResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

data class GetDetailMateriIbadahModel (
    val id: Int,
    val type: String,
    val title: String,
    val description: String,
    val file: List<FileItem>
) {
    data class FileItem(
        val url: String
    )
    companion object {
        fun mapResponseToModel(response: GetDetailMateriIbadahResponse): Flow<GetDetailMateriIbadahModel> {
            return flowOf(
                GetDetailMateriIbadahModel(
                    id = response.data?.id ?:0,
                    type = response.data?.type ?:"",
                    title = response.data?.title ?:"",
                    description = response.data?.description ?:"",
                    file = response.data?.file?.map { item ->
                        FileItem(
                            url = item?.url ?: ""
                        )
                    } ?:emptyList()
                )
            )
        }
    }
}