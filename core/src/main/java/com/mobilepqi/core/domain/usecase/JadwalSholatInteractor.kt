package com.mobilepqi.core.domain.usecase

import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.domain.model.JadwalSholatModel
import com.mobilepqi.core.domain.repository.JadwalSholatRepository
import kotlinx.coroutines.flow.Flow

class JadwalSholatInteractor(private val jadwalSholatRepository: JadwalSholatRepository) :
    JadwalSholatUsecase {

    override fun getJadwalSholat(
        latitude: String,
        longitude: String
    ): Flow<Resource<JadwalSholatModel>> = jadwalSholatRepository.getJadwalSholat(latitude, longitude)

}