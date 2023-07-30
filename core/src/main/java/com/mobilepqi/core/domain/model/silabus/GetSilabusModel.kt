package com.mobilepqi.core.domain.model.silabus

import com.mobilepqi.core.data.source.remote.response.silabus.GetSilabusResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class GetSilabusModel (
    val id: Int,
    val silabus: String
) {
    companion object {
        fun mapResponseToModel(response: GetSilabusResponse): Flow<GetSilabusModel> {
            return flowOf(
                GetSilabusModel(
                    id = response.data?.id ?: 0,
                    silabus = response.data?.silabus ?: ""
                )
            )
        }
    }
}