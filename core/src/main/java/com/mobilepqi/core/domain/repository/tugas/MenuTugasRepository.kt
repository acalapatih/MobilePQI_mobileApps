package com.mobilepqi.core.domain.repository.tugas

import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.data.source.remote.response.tugas.CreateNilaiPayload
import com.mobilepqi.core.data.source.remote.response.tugas.CreateTugasPayload
import com.mobilepqi.core.data.source.remote.response.tugas.UpdateDetailTugasPayload
import com.mobilepqi.core.domain.model.tugas.*
import kotlinx.coroutines.flow.Flow

interface MenuTugasRepository {
    fun getListTugas(idKelas: Int): Flow<Resource<GetListTugasModel>>
    fun createTugas(payload: CreateTugasPayload, idKelas: Int): Flow<Resource<Boolean>>
    fun getListTopicTugas(idKelas: Int, topic: String): Flow<Resource<GetListTopicTugasModel>>
    fun getDetailTugas(idTugas: Int): Flow<Resource<GetDetailTugasModel>>
    fun updateDetailTugas(payload: UpdateDetailTugasPayload, idTugas: Int): Flow<Resource<Boolean>>
    fun getListTugasMahasiswa(idTugas: Int, page: Int? = null, limit: Int? = null): Flow<Resource<GetListTugasMahasiswaModel>>
    fun getJawabanForDosen(idTugas: Int, nim: String): Flow<Resource<GetJawabanForDosenModel>>
    fun createNilai(payload: CreateNilaiPayload, idJawaban: Int): Flow<Resource<Boolean>>
}