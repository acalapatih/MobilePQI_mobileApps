package com.mobilepqi.core.domain.model.menuibadah

import com.mobilepqi.core.data.source.remote.response.ibadah.DeleteMateriIbadahResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

data class DeleteMateriIbadahModel(
    val status: Int,
    val message: String,
    val data: Data? = null,
) {
    data class Data(
        var errors: String
    )
    companion object {
        fun mapResponseToModel(response: DeleteMateriIbadahResponse): Flow<DeleteMateriIbadahModel> {
            return flowOf(
                DeleteMateriIbadahModel(
                    status = response.status ?:0,
                    message = response.message ?:"",
                    data = Data(errors = response.data?.errors ?:"")
                )
            )
        }
    }
}
