package com.mobilepqi.core.domain.model.menuibadah

import com.mobilepqi.core.data.source.remote.response.ibadah.GetMateriIbadahResponse
import com.mobilepqi.core.domain.model.common.DataMateri
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

data class GetMateriIbadahModel(
    val materi: List<DataMateri>,
){
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