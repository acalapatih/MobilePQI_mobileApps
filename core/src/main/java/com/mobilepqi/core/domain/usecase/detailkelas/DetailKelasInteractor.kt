package com.mobilepqi.core.domain.usecase.detailkelas

import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.domain.model.detailkelas.DetailKelasModel
import com.mobilepqi.core.domain.repository.detailkelas.DetailKelasRepository
import kotlinx.coroutines.flow.Flow

class DetailKelasInteractor(
    private val repository: DetailKelasRepository
): DetailKelasUsecase {
    override fun detailkelas(): Flow<Resource<DetailKelasModel>> = repository.detailkelas()
}