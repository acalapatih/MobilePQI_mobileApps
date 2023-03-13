package com.mobilepqi.core.domain.repository.profil

import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.data.source.remote.response.profil.PutProfilPayload
import com.mobilepqi.core.domain.model.profil.PutProfilModel
import kotlinx.coroutines.flow.Flow

interface PutProfilRepository {
    fun putprofil(request: PutProfilPayload): Flow<Resource<PutProfilModel>>
}