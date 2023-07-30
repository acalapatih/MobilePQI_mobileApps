package com.mobilepqi.core.domain.repository.jadwalsholat

import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.domain.model.jadwalsholat.JadwalSholatModel
import kotlinx.coroutines.flow.Flow

interface JadwalSholatRepository {
    fun getJadwalSholat(
        timestamp: String,
        latitude: String,
        longitude: String
    ): Flow<Resource<JadwalSholatModel>>
}