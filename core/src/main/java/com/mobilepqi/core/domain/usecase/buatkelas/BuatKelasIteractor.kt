package com.mobilepqi.core.domain.usecase.buatkelas

import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.data.source.remote.response.buatkelas.BuatKelasPayload
import com.mobilepqi.core.domain.model.buatkelas.BuatKelasModel
import com.mobilepqi.core.domain.repository.buatkelas.BuatKelasRepository
import kotlinx.coroutines.flow.Flow

class BuatKelasIteractor(
    private val repository: BuatKelasRepository
): BuatKelasUsecase {
    override fun buatkelas(request: BuatKelasPayload): Flow<Resource<BuatKelasModel>> =
        repository.buatkelas(request)
}