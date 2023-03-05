package com.mobilepqi.core.domain.usecase.daftarkelas

import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.domain.model.daftarkelas.DaftarKelasModel
import com.mobilepqi.core.domain.repository.daftarkelas.DaftarKelasRepository
import kotlinx.coroutines.flow.Flow

class DaftarKelasInteractor(
    private val repository: DaftarKelasRepository
): DaftarKelasUsecase {
    override fun daftarkelas(): Flow<Resource<DaftarKelasModel>> = repository.daftarkelas()
}