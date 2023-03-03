package com.mobilepqi.core.domain.usecase.tambahdosen

import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.data.source.remote.response.tambahdosen.PostTambahDosenPayload
import com.mobilepqi.core.domain.model.tambahdosen.GetTambahDosenModel
import com.mobilepqi.core.domain.model.tambahdosen.PostTambahDosenModel
import com.mobilepqi.core.domain.repository.tambahdosen.TambahDosenRepository
import kotlinx.coroutines.flow.Flow

class TambahDosenInteractor(
    private val repository: TambahDosenRepository
): TambahDosenUsecase {
    override fun getTambahDosen(idKelas: Int): Flow<Resource<GetTambahDosenModel>> = repository.getTambahDosen(idKelas)

    override fun postTambahDosen(request: PostTambahDosenPayload, idKelas: Int): Flow<Resource<PostTambahDosenModel>> = repository.postTambahDosen(request, idKelas)
}