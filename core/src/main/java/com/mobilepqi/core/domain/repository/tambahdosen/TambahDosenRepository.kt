package com.mobilepqi.core.domain.repository.tambahdosen

import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.data.source.remote.response.tambahdosen.PostTambahDosenPayload
import com.mobilepqi.core.domain.model.tambahdosen.GetTambahDosenModel
import com.mobilepqi.core.domain.model.tambahdosen.PostTambahDosenModel
import kotlinx.coroutines.flow.Flow

interface TambahDosenRepository {
    fun getTambahDosen(idKelas: Int): Flow<Resource<GetTambahDosenModel>>

    fun postTambahDosen(request: PostTambahDosenPayload, idKelas: Int): Flow<Resource<PostTambahDosenModel>>
}