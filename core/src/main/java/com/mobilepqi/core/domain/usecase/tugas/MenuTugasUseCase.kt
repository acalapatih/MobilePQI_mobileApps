package com.mobilepqi.core.domain.usecase.tugas

import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.data.source.remote.response.tugas.CreateTugasPayload
import com.mobilepqi.core.domain.model.tugas.GetDetailTugasModel
import com.mobilepqi.core.domain.model.tugas.GetListTopicTugasModel
import com.mobilepqi.core.domain.model.tugas.GetListTugasModel
import kotlinx.coroutines.flow.Flow

interface MenuTugasUseCase {
    fun getListTugas(idKelas: Int): Flow<Resource<GetListTugasModel>>
    fun createTugas(request: CreateTugasPayload ,idKelas: Int): Flow<Resource<Boolean>>
    fun getLisTopicTugas(idKelas: Int, topic: String): Flow<Resource<GetListTopicTugasModel>>
    fun getDetailTugas(idTugas: Int): Flow<Resource<GetDetailTugasModel>>
}