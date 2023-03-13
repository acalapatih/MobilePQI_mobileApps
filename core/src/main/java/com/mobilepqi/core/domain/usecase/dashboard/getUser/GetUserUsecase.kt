package com.mobilepqi.core.domain.usecase.dashboard.getUser

import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.domain.model.dashboard.GetUserModel
import kotlinx.coroutines.flow.Flow

interface GetUserUsecase {
    fun getUser(): Flow<Resource<GetUserModel>>
}