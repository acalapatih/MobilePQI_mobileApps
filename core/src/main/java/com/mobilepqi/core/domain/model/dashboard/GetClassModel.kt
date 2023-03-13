package com.mobilepqi.core.domain.model.dashboard

import com.mobilepqi.core.data.source.remote.response.dashboard.GetClassResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

data class GetClassModel(
    val id: Int,
    val name: String,
    val ruang: String,
    val jadwal: String,
    val code: String
) {
    companion object {
        fun mapResponseToModel(response: GetClassResponse): Flow<GetClassModel> {
            return flowOf(
                GetClassModel(
                    id = response.data?.id ?: 0,
                    name = response.data?.name ?: "",
                    ruang = response.data?.ruang ?: "",
                    jadwal = response.data?.jadwal ?: "",
                    code = response.data?.code ?: ""
                )
            )
        }
    }
}
