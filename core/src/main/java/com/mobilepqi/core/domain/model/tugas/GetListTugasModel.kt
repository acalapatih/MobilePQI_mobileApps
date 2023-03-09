package com.mobilepqi.core.domain.model.tugas

import com.mobilepqi.core.data.source.remote.response.tugas.GetListTugasResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

data class GetListTugasModel(
    val kelasId: Int,
    val qiroah: List<TugasItem>,
    val ibadah: List<TugasItem>,
    val surah: List<TugasItem>,
    val doa: List<TugasItem>,
) {
    data class TugasItem(
        val id: Int?,
        val title: String?,
        val deadline: String?,
        val status: Boolean?,
    )
    companion object {
        fun mapResponseToModel(response: GetListTugasResponse): Flow<GetListTugasModel> {
            return flowOf(
                GetListTugasModel(
                    kelasId = response.data?.kelasId ?:0,
                    qiroah = response.data?.qiroah?.map { tugas ->
                        TugasItem(
                            id = tugas?.id,
                            title = tugas?.title,
                            deadline = tugas?.deadline,
                            status = tugas?.status
                        )
                    } ?: emptyList(),
                    ibadah = response.data?.ibadah?.map { tugas ->
                        TugasItem(
                            id = tugas?.id,
                            title = tugas?.title,
                            deadline = tugas?.deadline,
                            status = tugas?.status
                        )
                    } ?: emptyList(),
                    surah = response.data?.surat?.map { tugas ->
                        TugasItem(
                            id = tugas?.id,
                            title = tugas?.title,
                            deadline = tugas?.deadline,
                            status = tugas?.status
                        )
                    } ?: emptyList(),
                    doa = response.data?.doa?.map { tugas ->
                        TugasItem(
                            id = tugas?.id,
                            title = tugas?.title,
                            deadline = tugas?.deadline,
                            status = tugas?.status
                        )
                    } ?: emptyList()
                )
            )
        }
    }

}
