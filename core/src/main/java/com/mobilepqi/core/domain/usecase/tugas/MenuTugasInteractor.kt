package com.mobilepqi.core.domain.usecase.tugas

import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.data.source.remote.response.tugas.CreateTugasPayload
import com.mobilepqi.core.domain.model.tugas.GetListTopicTugasModel
import com.mobilepqi.core.domain.model.tugas.GetListTugasModel
import com.mobilepqi.core.domain.repository.tugas.MenuTugasRepository
import kotlinx.coroutines.flow.Flow

class MenuTugasInteractor(
    private val repository: MenuTugasRepository,
) : MenuTugasUseCase {
    override fun getListTugas(idKelas: Int): Flow<Resource<GetListTugasModel>> =
        repository.getListTugas(idKelas)

    override fun createTugas(request: CreateTugasPayload, idKelas: Int): Flow<Resource<Boolean>> =
        repository.createTugas(request, idKelas)

    override fun getLisTopicTugas(
        idKelas: Int,
        topic: String,
    ): Flow<Resource<GetListTopicTugasModel>> = repository.getListTopicTugas(idKelas, topic)
}