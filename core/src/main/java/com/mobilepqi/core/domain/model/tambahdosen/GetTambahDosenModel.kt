package com.mobilepqi.core.domain.model.tambahdosen

import com.mobilepqi.core.data.source.remote.response.tambahdosen.GetTambahDosenResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

data class GetTambahDosenModel(
    val dosencount: Int,
    val dosenregistered: Int,
    val list: List<GetTambahDosen>
) {
    data class GetTambahDosen(
        val name: String,
        val avatar: String,
        val nip: String
    ) companion object {
        fun mapResponseToModel(response: GetTambahDosenResponse): Flow<GetTambahDosenModel> {
            return flowOf(
                GetTambahDosenModel(
                    dosencount = response.data?.dosenCount ?: 0,
                    dosenregistered = response.data?.dosenRegistered ?: 0,
                    list = response.data?.dosens?.map {
                        GetTambahDosen(
                            name = it?.name ?: "",
                            avatar = it?.avatar ?: "",
                            nip = it?.nim ?: ""
                        )
                    } ?: emptyList()
                )
            )
        }
    }
}