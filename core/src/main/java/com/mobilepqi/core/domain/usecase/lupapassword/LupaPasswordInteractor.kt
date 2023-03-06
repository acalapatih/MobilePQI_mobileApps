package com.mobilepqi.core.domain.usecase.lupapassword

import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.data.source.remote.response.lupapassword.LupaPasswordPayload
import com.mobilepqi.core.domain.repository.lupapassword.LupaPasswordRepository
import kotlinx.coroutines.flow.Flow

class LupaPasswordInteractor(
    private val repository: LupaPasswordRepository
) : LupaPasswordUsecase {
    override fun lupaPassword(request: LupaPasswordPayload): Flow<Resource<Boolean>> =
        repository.lupaPassword(request)
}