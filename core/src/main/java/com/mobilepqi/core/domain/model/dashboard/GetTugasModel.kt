package com.mobilepqi.core.domain.model.dashboard

import com.mobilepqi.core.data.source.remote.response.dashboard.Data
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

data class GetTugasModel(
    val idKelas: Int,
    val tugasCount: Int,
    val idPraktikum: Int,
    val jdPraktikum: String,
    val dlPraktikum: String
) {
    companion object {
        fun mapResponseToModel(response: Data.GetTugasResponse): Flow<GetTugasModel> {
            return flowOf(
                GetTugasModel(
                    idKelas = response.data?.kelasId ?: 0,
                    tugasCount = response.data?.tugasCount ?: 0,
                    idPraktikum = response.data?.praktikum?.id ?: 0,
                    jdPraktikum = response.data?.praktikum?.title ?: "",
                    dlPraktikum = response.data?.praktikum?.deadline ?: ""
                )
            )
        }
    }
}
