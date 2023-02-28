package com.mobilepqi.core.domain.model.tambahdosen

import com.mobilepqi.core.data.source.remote.response.tambahdosen.TambahDosenResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

data class TambahDosenModel(
    val dosencount: Int,
    val list: List<TambahDosen>
) {
    data class TambahDosen(
        val name: String,
        val avatar: String,
        val nim: String
    ) companion object {
        fun mapResponseToModel(response: TambahDosenResponse): Flow<TambahDosenModel> {
            return flowOf(
                TambahDosenModel(
                    dosencount = response.data?.dosenCount ?: 0,
                    list = response.data?.dosens?.map {
                        TambahDosen(
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