package com.mobilepqi.core.domain.model.menuqiroah

import com.mobilepqi.core.data.source.remote.response.qiroah.DeleteMateriQiroahResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

data class DeleteMateriQiroahModel(
    val status: Int,
    val message: String,
    val data: Data? = null,
) {
    data class Data(
        var errors: String
    )
    companion object {
        fun mapResponseToModel(response: DeleteMateriQiroahResponse): Flow<DeleteMateriQiroahModel> {
            return flowOf(
                DeleteMateriQiroahModel(
                    status = response.status ?:0,
                    message = response.message ?:"",
                    data = Data(errors = response.data?.errors ?:"")
                )
            )
        }
    }
}
