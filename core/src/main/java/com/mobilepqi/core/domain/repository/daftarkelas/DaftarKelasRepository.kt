package com.mobilepqi.core.domain.repository.daftarkelas

import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.domain.model.daftarkelas.DaftarKelasModel
import kotlinx.coroutines.flow.Flow

interface DaftarKelasRepository {
    fun daftarkelas(): Flow<Resource<DaftarKelasModel>>
}