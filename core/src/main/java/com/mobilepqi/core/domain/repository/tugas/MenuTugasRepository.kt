package com.mobilepqi.core.domain.repository.tugas

import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.data.source.remote.response.tugas.CreateTugasPayload
import com.mobilepqi.core.data.source.remote.response.tugas.UpdateDetailTugasPayload
import com.mobilepqi.core.domain.model.tugas.GetDetailTugasModel
import com.mobilepqi.core.domain.model.tugas.GetListTopicTugasModel
import com.mobilepqi.core.domain.model.tugas.GetListTugasMahasiswaModel
import com.mobilepqi.core.domain.model.tugas.GetListTugasModel
import kotlinx.coroutines.flow.Flow

interface MenuTugasRepository {
    fun getListTugas(idKelas: Int): Flow<Resource<GetListTugasModel>>
    fun createTugas(payload: CreateTugasPayload, idKelas: Int): Flow<Resource<Boolean>>
    fun getListTopicTugas(idKelas: Int, topic: String): Flow<Resource<GetListTopicTugasModel>>
    fun getDetailTugas(idTugas: Int): Flow<Resource<GetDetailTugasModel>>
    fun updateDetailTugas(payload: UpdateDetailTugasPayload, idTugas: Int): Flow<Resource<Boolean>>
    fun getListTugasMahasiswa(idTugas: Int, page: Int, limit: Int): Flow<Resource<GetListTugasMahasiswaModel>>
}