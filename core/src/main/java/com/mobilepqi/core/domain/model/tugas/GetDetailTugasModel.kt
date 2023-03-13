package com.mobilepqi.core.domain.model.tugas

import com.mobilepqi.core.data.source.remote.response.tugas.GetDetailTugasResponse
import com.mobilepqi.core.domain.model.common.FileItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

data class GetDetailTugasModel(
    val file: List<FileItem>,
    val updateAt: String,
    val jenis: String,
    val description: String,
    val topic: String,
    val id: Int,
    val title: String,
    val deadline: String,
    val createdBy: String,
    val status: Boolean,
) {
    companion object {
        fun mapResponseToModel(response: GetDetailTugasResponse): Flow<GetDetailTugasModel> {
            return flowOf(
                GetDetailTugasModel(
                    file = response.data?.file?.map { tugas ->
                        FileItem(
                            url = tugas?.url ?: ""
                        )
                    } ?: emptyList(),
                    updateAt = response.data?.updatedAt ?: "",
                    jenis = response.data?.jenis ?: "",
                    description = response.data?.description ?: "",
                    topic = response.data?.topic ?: "",
                    id = response.data?.id ?: 0,
                    title = response.data?.title ?: "",
                    deadline = response.data?.deadline ?: "",
                    createdBy = response.data?.createdBy ?: "",
                    status = response.data?.status ?: true
                )
            )
        }
    }
}
