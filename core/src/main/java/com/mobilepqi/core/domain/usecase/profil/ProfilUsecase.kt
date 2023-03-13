package com.mobilepqi.core.domain.usecase.profil

import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.domain.model.profil.ProfilModel
import kotlinx.coroutines.flow.Flow

interface ProfilUsecase {
    fun profil(): Flow<Resource<ProfilModel>>
}