package com.mobilepqi.core.domain.usecase.silabus

import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.data.source.remote.response.silabus.CreateSilabusPayload
import com.mobilepqi.core.domain.model.silabus.GetSilabusModel
import com.mobilepqi.core.domain.repository.silabus.SilabusRepository
import kotlinx.coroutines.flow.Flow

class SilabusInteractor(
    private val repository: SilabusRepository
) : SilabusUsecase {
    override fun createSilabus(request: CreateSilabusPayload, idKelas: Int): Flow<Resource<Boolean>> =
        repository.createSilabus(request, idKelas)

    override fun getSilabus(idKelas: Int): Flow<Resource<GetSilabusModel>> =
        repository.getSilabus(idKelas)

    override fun deleteSilabus(idKelas: Int): Flow<Resource<Boolean>> =
        repository.deleteSilabus(idKelas)
}