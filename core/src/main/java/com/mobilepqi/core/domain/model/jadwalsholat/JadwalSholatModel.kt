package com.mobilepqi.core.domain.model.jadwalsholat

import com.mobilepqi.core.data.source.remote.response.jadwalsholat.JadwalSholatResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

data class JadwalSholatModel(
    val subuh: String,
    val zuhur: String,
    val ashar: String,
    val maghrib: String,
    val isya: String
) {
    companion object {
        fun mapResponseToModel(response: JadwalSholatResponse): Flow<JadwalSholatModel> {
            return flowOf(
                JadwalSholatModel(
                    subuh = response.data?.timings?.fajr ?: "",
                    zuhur = response.data?.timings?.dhuhr ?: "",
                    ashar = response.data?.timings?.asr ?: "",
                    maghrib = response.data?.timings?.maghrib ?: "",
                    isya = response.data?.timings?.isha ?: ""
                )
            )
        }
    }
}
