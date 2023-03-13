package com.mobilepqi.core.domain.model.tugas

import com.mobilepqi.core.data.source.remote.response.tugas.GetListTopicTugasResponse
import com.mobilepqi.core.domain.model.common.TugasItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

data class GetListTopicTugasModel(
    val tugas: List<TugasItem>? = null,
    val tugasCount: Int,
    val kelasId: Int
) {
    companion object {
        fun mapResponseToModel(response: GetListTopicTugasResponse): Flow<GetListTopicTugasModel> {
            return flowOf(
                GetListTopicTugasModel(
                    tugas = response.data?.tugas?.map { tugas ->
                        TugasItem(
                            id = tugas?.id ?:0,
                            title = tugas?.title ?:"",
                            deadline = tugas?.deadline ?:"",
                            status = tugas?.status ?:false
                        )
                    } ?: emptyList(),
                    tugasCount = response.data?.tugasCount ?:0,
                    kelasId = response.data?.kelasId ?:0
                )
            )
        }
    }
}
