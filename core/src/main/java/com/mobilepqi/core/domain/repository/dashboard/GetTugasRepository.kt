package com.mobilepqi.core.domain.repository.dashboard

import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.domain.model.dashboard.GetTugasModel
import kotlinx.coroutines.flow.Flow

interface GetTugasRepository {
    fun getTugas(idKelas: Int): Flow<Resource<GetTugasModel>>
}