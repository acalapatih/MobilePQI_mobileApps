package com.mobilepqi.core.domain.model.menuqiroah

import com.mobilepqi.core.data.source.remote.response.qiroah.GetMateriQiroahResponse
import com.mobilepqi.core.domain.model.common.DataMateri
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

data class GetMateriQiroahModel(
    val materi: List<DataMateri>,
){
    companion object {
        fun mapResponseToModel(response: GetMateriQiroahResponse): Flow<GetMateriQiroahModel> {
            return flowOf(
                GetMateriQiroahModel(
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