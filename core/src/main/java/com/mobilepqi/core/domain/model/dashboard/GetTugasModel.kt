package com.mobilepqi.core.domain.model.dashboard

import com.mobilepqi.core.data.source.remote.response.dashboard.GetTugasResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

data class GetTugasModel(
    val idKelas: Int,
    val tugasCount: Int,
    val listTugas: List<ListTugas>
) {
    data class ListTugas(
        val idTugas: Int,
        val title: String,
        val deadline: String,
        val createdAt: String,
        val status: Boolean
    ) companion object {
        fun mapResponseToModel(response: GetTugasResponse): Flow<GetTugasModel> {
            return flowOf(
                GetTugasModel(
                    idKelas = response.data?.kelasId ?: 0,
                    tugasCount = response.data?.tugasCount ?: 0,
                    listTugas = response.data?.tugas?.map {
                        ListTugas(
                            idTugas = it?.id ?: 0,
                            title = it?.title ?: "",
                            deadline = it?.deadline ?: "",
                            createdAt = it?.createdAt ?: "",
                            status = it?.status ?: false
                        )
                    } ?: emptyList()
                )
            )
        }
    }
}
