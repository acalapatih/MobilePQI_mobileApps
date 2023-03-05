package com.mobilepqi.core.domain.model.menuibadah

import com.mobilepqi.core.data.source.remote.response.ibadah.GetMateriIbadahResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

data class GetMateriIbadahModel(
    val materi: List<DataMateri>,
){
    data class DataMateri(
        val id: Int,
        val title: String
    )
    companion object {
        fun mapResponseToModel(response: GetMateriIbadahResponse): Flow<GetMateriIbadahModel> {
            return flowOf(
                GetMateriIbadahModel(
                    materi = response.data?.materi?.map {
                        DataMateri(
                            id = it?.id ?: 0,
                            title = it?.title ?: ""
                        )
                    } ?:emptyList()
                )
            )
        }
    }
}