package com.mobilepqi.core.domain.usecase.jadwalsholat

import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.domain.model.jadwalsholat.JadwalSholatModel
import kotlinx.coroutines.flow.Flow

interface JadwalSholatUsecase {

    fun getJadwalSholat(
        timestamp: String,
        latitude: String,
        longitude: String
    ): Flow<Resource<JadwalSholatModel>>

}