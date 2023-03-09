package com.mobilepqi.core.domain.repository.tugas

import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.data.source.remote.response.tugas.CreateTugasPayload
import com.mobilepqi.core.domain.model.tugas.GetListTugasModel
import kotlinx.coroutines.flow.Flow

interface MenuTugasRepository {
    fun getListTugas(idKelas: Int): Flow<Resource<GetListTugasModel>>
    fun createTugas(payload: CreateTugasPayload, idKelas: Int): Flow<Resource<Boolean>>
}