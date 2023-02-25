package com.mobilepqi.core.domain.usecase.signup

import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.data.source.remote.response.signup.SignupPayload
import kotlinx.coroutines.flow.Flow

interface SignupUsecase {
    fun signup(request: SignupPayload): Flow<Resource<Boolean>>
}