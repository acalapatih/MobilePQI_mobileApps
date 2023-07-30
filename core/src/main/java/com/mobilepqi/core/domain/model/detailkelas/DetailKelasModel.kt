package com.mobilepqi.core.domain.model.detailkelas

import com.mobilepqi.core.data.source.remote.response.detailkelas.DetailKelasResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

data class DetailKelasModel(
    val id: Int,
    val name: String,
    val ruang: String,
    val jadwal: String,
    val code: String,
    val mahasiswaCount: Int,
    val listdosen: List<ListDosen>,
    val listmahasiswa: List<ListMahasiswa>
) {
    data class ListDosen(
        val name: String,
        val avatar: String,
        val nip: String
    )
    data class ListMahasiswa(
        val name: String,
        val avatar: String,
        val nim: String
    ) companion object {
        fun mapResponseToModel(response: DetailKelasResponse): Flow<DetailKelasModel> {
            return flowOf(
                DetailKelasModel(
                    id = response.data?.id ?: 0,
                    name = response.data?.name ?: "",
                    ruang = response.data?.ruang ?: "",
                    jadwal = response.data?.jadwal ?: "",
                    code = response.data?.code ?: "",
                    mahasiswaCount = response.data?.id ?: 0,
                    listdosen = response.data?.listDosen?.map {
                        ListDosen(
                            name = it?.name ?: "",
                            avatar = it?.avatar ?: "",
                            nip = it?.nim ?: ""
                        )
                    } ?: emptyList(),
                    listmahasiswa = response.data?.listMahasiswa?.map {
                        ListMahasiswa(
                            name = it?.name ?: "",
                            avatar = it?.avatar ?: "",
                            nim = it?.nim ?: ""
                        )
                    } ?: emptyList()
                )
            )
        }
    }
}
