package com.mobilepqi.core.domain.repository.tambahdosen

import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.domain.model.tambahdosen.TambahDosenModel
import kotlinx.coroutines.flow.Flow

interface TambahDosenRepository {
    fun tambahdosen(): Flow<Resource<TambahDosenModel>>
}