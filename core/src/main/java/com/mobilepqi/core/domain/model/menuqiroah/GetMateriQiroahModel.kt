package com.mobilepqi.core.domain.model.menuqiroah

import com.mobilepqi.core.data.source.remote.response.menuqiroah.GetMateriQiroahResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

data class GetMateriQiroahModel(
    val materi: List<DataMateri>,
){
    data class DataMateri(
        val id: Int,
        val title: String
    )
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