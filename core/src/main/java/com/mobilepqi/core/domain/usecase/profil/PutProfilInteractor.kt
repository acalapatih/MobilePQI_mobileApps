package com.mobilepqi.core.domain.usecase.profil

import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.data.source.remote.response.profil.PutProfilPayload
import com.mobilepqi.core.domain.model.profil.PutProfilModel
import com.mobilepqi.core.domain.repository.profil.PutProfilRepository
import kotlinx.coroutines.flow.Flow

class PutProfilInteractor(
    private val repository: PutProfilRepository
): PutProfilUsecase {
    override fun putprofil(request: PutProfilPayload): Flow<Resource<PutProfilModel>> =
        repository.putprofil(request)
}