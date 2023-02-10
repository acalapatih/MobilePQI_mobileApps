package com.mobilepqi.core.domain.repository

import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.domain.model.JadwalSholatModel
import kotlinx.coroutines.flow.Flow

interface JadwalSholatRepository {
    fun getJadwalSholat(latitude: String, longitude: String): Flow<Resource<JadwalSholatModel>>
}