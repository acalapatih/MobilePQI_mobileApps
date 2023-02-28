package com.mobilepqi.core.domain.usecase.tambahdosen

import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.domain.model.tambahdosen.TambahDosenModel
import com.mobilepqi.core.domain.repository.tambahdosen.TambahDosenRepository
import kotlinx.coroutines.flow.Flow

class TambahDosenInteractor(
    private val repository: TambahDosenRepository
): TambahDosenUsecase {
    override fun tambahdosen(): Flow<Resource<TambahDosenModel>> = repository.tambahdosen()
}