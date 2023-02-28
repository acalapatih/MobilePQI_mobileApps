package com.mobilepqi.core.domain.usecase.tambahdosen

import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.domain.model.tambahdosen.TambahDosenModel
import kotlinx.coroutines.flow.Flow

interface TambahDosenUsecase {
    fun tambahdosen(): Flow<Resource<TambahDosenModel>>
}