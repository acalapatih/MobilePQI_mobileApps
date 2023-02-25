package com.mobilepqi.core.domain.usecase.signup

import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.data.source.remote.response.signup.SignupPayload
import com.mobilepqi.core.domain.repository.signup.SignupRepository
import kotlinx.coroutines.flow.Flow

class SignupInteractor(
    private val repository: SignupRepository
) : SignupUsecase {
    override fun signup(request: SignupPayload): Flow<Resource<Boolean>> =
        repository.signup(request)
}