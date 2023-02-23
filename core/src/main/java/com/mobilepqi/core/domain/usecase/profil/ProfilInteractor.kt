package com.mobilepqi.core.domain.usecase.profil

import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.domain.model.profil.ProfilModel
import com.mobilepqi.core.domain.repository.profil.ProfilRepository
import kotlinx.coroutines.flow.Flow

class ProfilInteractor(
    private val repository: ProfilRepository
): ProfilUsecase {
    override fun profil(): Flow<Resource<ProfilModel>> = repository.profil()
}