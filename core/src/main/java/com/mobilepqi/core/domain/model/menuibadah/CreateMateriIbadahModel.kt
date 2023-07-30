package com.mobilepqi.core.domain.model.menuibadah

import com.mobilepqi.core.data.source.remote.response.ibadah.CreateMateriIbadahResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

data class CreateMateriIbadahModel (
    val id: Int,
    val createdBy: String,
    val type: String,
    val title: String
) {
    companion object {
        fun mapResponseToModel(response: CreateMateriIbadahResponse): Flow<CreateMateriIbadahModel> {
            return flowOf(
                CreateMateriIbadahModel(
                    id = response.data?.id ?:0,
                    createdBy = response.data?.createdBy ?:"",
                    type = response.data?.type ?:"",
                    title = response.data?.title ?:"",
                )
            )
        }
    }
}