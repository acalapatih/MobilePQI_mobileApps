package com.mobilepqi.core.domain.usecase.detailkelas

import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.domain.model.detailkelas.DetailKelasModel
import kotlinx.coroutines.flow.Flow

interface DetailKelasUsecase {
    fun detailkelas(idKelas: Int): Flow<Resource<DetailKelasModel>>
}