package com.mobilepqi.core.domain.repository.dashboard

import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.domain.model.dashboard.GetClassModel
import kotlinx.coroutines.flow.Flow

interface GetClassRepository {
    fun getClass(idKelas: Int): Flow<Resource<GetClassModel>>
}