package com.mobilepqi.core.domain.repository.profil

import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.domain.model.profil.ProfilModel
import kotlinx.coroutines.flow.Flow

interface ProfilRepository {
    fun profil(): Flow<Resource<ProfilModel>>
}