package com.mobilepqi.core.domain.usecase.dashboard.getClass

import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.domain.model.dashboard.GetClassModel
import com.mobilepqi.core.domain.repository.dashboard.GetClassRepository
import kotlinx.coroutines.flow.Flow

class GetClassInteractor(
    private val repository: GetClassRepository
): GetClassUsecase {
    override fun getClass(idKelas: Int): Flow<Resource<GetClassModel>> = repository.getClass(idKelas)
}