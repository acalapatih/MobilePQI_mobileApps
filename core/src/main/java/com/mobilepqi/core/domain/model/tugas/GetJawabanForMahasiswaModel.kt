package com.mobilepqi.core.domain.model.tugas

import com.mobilepqi.core.data.source.remote.response.tugas.GetJawabanForMahasiswaResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

data class GetJawabanForMahasiswaModel(
    val tugasId: Int,
    val nim: String,
    val file: String,
    val updatedAt: String,
    val nilai: Int,
    val createdAt: String,
    val id: Int
) {
    companion object {
        fun mapResponseToModel(response: GetJawabanForMahasiswaResponse): Flow<GetJawabanForMahasiswaModel> {
            return flowOf(
                GetJawabanForMahasiswaModel(
                    tugasId = response.data?.tugasId ?: 0,
                    nim = response.data?.nim ?: "",
                    file = response.data?.file ?: "",
                    updatedAt = response.data?.updatedAt ?: "",
                    nilai = response.data?.nilai ?: 0,
                    createdAt = response.data?.createdAt ?: "",
                    id = response.data?.id ?: 0
                )
            )
        }
    }
}
