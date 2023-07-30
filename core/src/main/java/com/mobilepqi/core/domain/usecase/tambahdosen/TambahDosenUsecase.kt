package com.mobilepqi.core.domain.usecase.tambahdosen

import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.data.source.remote.response.tambahdosen.PostTambahDosenPayload
import com.mobilepqi.core.domain.model.tambahdosen.GetTambahDosenModel
import com.mobilepqi.core.domain.model.tambahdosen.PostTambahDosenModel
import kotlinx.coroutines.flow.Flow

interface TambahDosenUsecase {
    fun getTambahDosen(idKelas: Int, namaNip: String): Flow<Resource<GetTambahDosenModel>>

    fun postTambahDosen(request: PostTambahDosenPayload, idKelas: Int): Flow<Resource<PostTambahDosenModel>>
}