package com.mobilepqi.core.domain.usecase.daftarkelas

import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.domain.model.daftarkelas.DaftarKelasModel
import kotlinx.coroutines.flow.Flow

interface DaftarKelasUsecase {
    fun daftarkelas(): Flow<Resource<DaftarKelasModel>>
}