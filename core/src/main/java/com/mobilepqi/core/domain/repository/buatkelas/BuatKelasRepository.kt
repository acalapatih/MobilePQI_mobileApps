package com.mobilepqi.core.domain.repository.buatkelas

import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.data.source.remote.response.buatkelas.BuatKelasPayload
import com.mobilepqi.core.domain.model.buatkelas.BuatKelasModel
import kotlinx.coroutines.flow.Flow

interface BuatKelasRepository {
    fun buatkelas(request: BuatKelasPayload): Flow<Resource<BuatKelasModel>>
}