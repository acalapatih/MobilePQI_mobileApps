package com.mobilepqi.core.domain.usecase.signin

import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.data.source.remote.response.signin.SigninPayload
import com.mobilepqi.core.domain.model.signin.SigninModel
import kotlinx.coroutines.flow.Flow

interface SigninUsecase {
    fun signin(request: SigninPayload): Flow<Resource<SigninModel>>
    fun setToken(token: String)
    fun setUserRole(role: String)
}