package com.mobilepqi.core.domain.usecase.dashboard.getTugas

import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.domain.model.dashboard.GetTugasModel
import kotlinx.coroutines.flow.Flow

interface GetTugasUsecase {
    fun getTugas(idKelas: Int): Flow<Resource<GetTugasModel>>
}