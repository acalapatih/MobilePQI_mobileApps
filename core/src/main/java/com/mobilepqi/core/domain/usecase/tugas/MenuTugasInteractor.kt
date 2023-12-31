package com.mobilepqi.core.domain.usecase.tugas

import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.data.source.remote.response.tugas.CreateJawabanPayload
import com.mobilepqi.core.data.source.remote.response.tugas.CreateNilaiPayload
import com.mobilepqi.core.data.source.remote.response.tugas.CreateTugasPayload
import com.mobilepqi.core.data.source.remote.response.tugas.UpdateDetailTugasPayload
import com.mobilepqi.core.domain.model.tugas.*
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

    override fun getDetailTugas(idTugas: Int): Flow<Resource<GetDetailTugasModel>> =
        repository.getDetailTugas(idTugas)

    override fun updateDetailTugas(
        request: UpdateDetailTugasPayload,
        idTugas: Int,
    ): Flow<Resource<Boolean>> = repository.updateDetailTugas(request, idTugas)

    override fun getListTugasMahasiswa(
        idTugas: Int,
        page: Int?,
        limit: Int?
    ): Flow<Resource<GetListTugasMahasiswaModel>> =
        repository.getListTugasMahasiswa(idTugas, page, limit)

    override fun getJawabanForDosen(
        idTugas: Int,
        nim: String,
    ): Flow<Resource<GetJawabanForDosenModel>> = repository.getJawabanForDosen(idTugas, nim)

    override fun createNilai(request: CreateNilaiPayload, idJawaban: Int): Flow<Resource<Boolean>> =
        repository.createNilai(request, idJawaban)

    override fun getJawabanForMahasiswa(idTugas: Int): Flow<Resource<GetJawabanForMahasiswaModel>> =
        repository.getJawabanForMahasiswa(idTugas)

    override fun createJawaban(
        request: CreateJawabanPayload,
        idTugas: Int
    ): Flow<Resource<Boolean>> = repository.createJawaban(request, idTugas)

    override fun deleteJawaban(idTugas: Int): Flow<Resource<Boolean>> =
        repository.deleteJawaban(idTugas)

    override fun downloadNilai(idKelas: Int, query: String?): Flow<Resource<DownloadNilaiModel>> =
        repository.downloadNilai(idKelas, query)
}