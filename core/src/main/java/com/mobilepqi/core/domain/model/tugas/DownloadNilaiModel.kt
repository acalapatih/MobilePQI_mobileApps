package com.mobilepqi.core.domain.model.tugas

import com.mobilepqi.core.data.source.remote.response.tugas.DownloadNilaiResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

data class DownloadNilaiModel(
    val kelasId: Int,
    val url: String
) {
    companion object {
        fun mapResponseToModel(response: DownloadNilaiResponse): Flow<DownloadNilaiModel> {
            return flowOf(
                DownloadNilaiModel(
                    kelasId = response.data?.kelasId ?: 0,
                    url = response.data?.url ?: ""
                )
            )
        }
    }
}
