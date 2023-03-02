package com.mobilepqi.core.domain.model.menuqiroah

import com.mobilepqi.core.data.source.remote.response.menuqiroah.GetDetailMateriQiroahResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

data class GetDetailMateriQiroahModel (
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
        fun mapResponseToModel(response: GetDetailMateriQiroahResponse): Flow<GetDetailMateriQiroahModel> {
            return flowOf(
                GetDetailMateriQiroahModel(
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