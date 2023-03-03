package com.mobilepqi.core.domain.repository.detailkelas

import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.domain.model.detailkelas.DetailKelasModel
import kotlinx.coroutines.flow.Flow

interface DetailKelasRepository {
    fun detailkelas(idKelas: Int): Flow<Resource<DetailKelasModel>>
}