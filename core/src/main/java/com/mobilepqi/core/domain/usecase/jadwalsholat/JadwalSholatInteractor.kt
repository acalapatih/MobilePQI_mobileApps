package com.mobilepqi.core.domain.usecase.jadwalsholat

import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.domain.model.jadwalsholat.JadwalSholatModel
import com.mobilepqi.core.domain.repository.jadwalsholat.JadwalSholatRepository
import kotlinx.coroutines.flow.Flow

class JadwalSholatInteractor(private val jadwalSholatRepository: JadwalSholatRepository) :
    JadwalSholatUsecase {

    override fun getJadwalSholat(
        timestamp: String,
        latitude: String,
        longitude: String
    ): Flow<Resource<JadwalSholatModel>> =
        jadwalSholatRepository.getJadwalSholat(timestamp, latitude, longitude)

}