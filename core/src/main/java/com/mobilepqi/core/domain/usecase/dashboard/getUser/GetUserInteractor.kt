package com.mobilepqi.core.domain.usecase.dashboard.getUser

import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.domain.model.dashboard.GetUserModel
import com.mobilepqi.core.domain.repository.dashboard.GetUserRepository
import kotlinx.coroutines.flow.Flow

class GetUserInteractor(
    private val repository: GetUserRepository
): GetUserUsecase {
    override fun getUser(): Flow<Resource<GetUserModel>> = repository.getUser()
}