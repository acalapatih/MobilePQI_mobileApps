package com.mobilepqi.core.domain.repository.silabus

import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.data.source.remote.response.silabus.CreateSilabusPayload
import com.mobilepqi.core.domain.model.silabus.GetSilabusModel
import kotlinx.coroutines.flow.Flow

interface SilabusRepository {
    fun createSilabus(request: CreateSilabusPayload, idKelas: Int): Flow<Resource<Boolean>>
    fun getSilabus(idKelas: Int): Flow<Resource<GetSilabusModel>>
    fun deleteSilabus(idKelas: Int): Flow<Resource<Boolean>>
}