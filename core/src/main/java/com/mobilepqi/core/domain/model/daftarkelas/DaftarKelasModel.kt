package com.mobilepqi.core.domain.model.daftarkelas

import com.mobilepqi.core.data.source.remote.response.daftarkelas.DaftarKelasResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

data class DaftarKelasModel(
    val kelascount: Int,
    val list: List<DaftarKelas>
) {
    data class DaftarKelas(
        val id: Int,
        val name: String,
        val ruang: String,
        val jadwal: String,
        val code: String,
        val members: Int
    ) companion object {
        fun mapResponseToModel(response: DaftarKelasResponse): Flow<DaftarKelasModel> {
            return flowOf(
                DaftarKelasModel(
                    kelascount = response.data?.kelasCount ?: 0,
                    list = response.data?.kelas?.map {
                        DaftarKelasModel.DaftarKelas(
                            id = it?.id ?: 0,
                            name = it?.name ?: "",
                            ruang = it?.ruang ?: "",
                            jadwal = it?.jadwal ?: "",
                            code = it?.jadwal ?: "",
                            members = it?.members ?: 0
                        )
                    } ?: emptyList()
                )
            )
        }
    }
}
