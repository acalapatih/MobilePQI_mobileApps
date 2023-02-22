package com.mobilepqi.core.domain.usecase.signin

import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.data.source.remote.response.signin.SigninPayload
import com.mobilepqi.core.domain.model.signin.SigninModel
import com.mobilepqi.core.domain.repository.signin.SigninRepository
import kotlinx.coroutines.flow.Flow

class SigninInteractor(
    private val repository: SigninRepository
) : SigninUsecase {
    override fun signin(request: SigninPayload): Flow<Resource<SigninModel>> =
        repository.signin(request)

    override fun setToken(token: String) {
        repository.setToken(token)
    }
}