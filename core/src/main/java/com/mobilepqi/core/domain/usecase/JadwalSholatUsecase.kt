package com.mobilepqi.core.domain.usecase

import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.domain.model.JadwalSholatModel
import kotlinx.coroutines.flow.Flow

interface JadwalSholatUsecase {

    fun getJadwalSholat(latitude: String, longitude: String): Flow<Resource<JadwalSholatModel>>

}