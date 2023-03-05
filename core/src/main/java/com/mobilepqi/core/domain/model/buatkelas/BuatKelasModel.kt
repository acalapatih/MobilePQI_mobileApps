package com.mobilepqi.core.domain.model.buatkelas

import com.mobilepqi.core.data.source.remote.response.buatkelas.BuatKelasResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

data class BuatKelasModel(
    val id: Int,
    val name: String,
    val ruang: String,
    val jadwal: String,
    val code: String
) {
    companion object {
        fun mapResponseToModel(response: BuatKelasResponse): Flow<BuatKelasModel> {
            return flowOf(
                BuatKelasModel(
                    id = response.data?.id ?: 0,
                    name = response.data?.name ?: "",
                    ruang = response.data?.ruang ?: "",
                    jadwal = response.data?.jadwal ?: "",
                    code = response.data?.code ?: "",
                )
            )
        }
    }
}
