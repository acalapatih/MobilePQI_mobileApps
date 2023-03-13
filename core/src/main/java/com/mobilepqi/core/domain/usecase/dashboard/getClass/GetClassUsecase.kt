package com.mobilepqi.core.domain.usecase.dashboard.getClass

import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.domain.model.dashboard.GetClassModel
import kotlinx.coroutines.flow.Flow

interface GetClassUsecase {
    fun getClass(idKelas: Int): Flow<Resource<GetClassModel>>
}