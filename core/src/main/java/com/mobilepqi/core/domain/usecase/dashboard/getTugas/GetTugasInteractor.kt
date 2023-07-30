package com.mobilepqi.core.domain.usecase.dashboard.getTugas

import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.domain.model.dashboard.GetTugasModel
import com.mobilepqi.core.domain.repository.dashboard.GetTugasRepository
import kotlinx.coroutines.flow.Flow

class GetTugasInteractor(
    private val repository: GetTugasRepository
): GetTugasUsecase {
    override fun getTugas(idKelas: Int): Flow<Resource<GetTugasModel>> = repository.getTugas(idKelas)
}