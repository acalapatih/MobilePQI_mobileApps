package com.mobilepqi.core.domain.repository.lupapassword

import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.data.source.remote.response.lupapassword.LupaPasswordPayload
import kotlinx.coroutines.flow.Flow

interface LupaPasswordRepository {
    fun lupaPassword(request: LupaPasswordPayload): Flow<Resource<Boolean>>
}