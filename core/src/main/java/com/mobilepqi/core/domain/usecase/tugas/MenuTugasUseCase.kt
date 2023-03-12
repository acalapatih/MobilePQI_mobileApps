package com.mobilepqi.core.domain.usecase.tugas

import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.data.source.remote.response.tugas.CreateNilaiPayload
import com.mobilepqi.core.data.source.remote.response.tugas.CreateTugasPayload
import com.mobilepqi.core.data.source.remote.response.tugas.UpdateDetailTugasPayload
import com.mobilepqi.core.domain.model.tugas.*
import kotlinx.coroutines.flow.Flow

interface MenuTugasUseCase {
    fun getListTugas(idKelas: Int): Flow<Resource<GetListTugasModel>>
    fun createTugas(request: CreateTugasPayload, idKelas: Int): Flow<Resource<Boolean>>
    fun getLisTopicTugas(idKelas: Int, topic: String): Flow<Resource<GetListTopicTugasModel>>
    fun getDetailTugas(idTugas: Int): Flow<Resource<GetDetailTugasModel>>
    fun updateDetailTugas(request: UpdateDetailTugasPayload, idTugas: Int): Flow<Resource<Boolean>>
    fun getListTugasMahasiswa(idTugas: Int, page: Int? = null, limit: Int? = null): Flow<Resource<GetListTugasMahasiswaModel>>
    fun getJawabanForDosen(idTugas: Int, nim: String): Flow<Resource<GetJawabanForDosenModel>>
    fun createNilai(request: CreateNilaiPayload, idJawaban: Int): Flow<Resource<Boolean>>
}