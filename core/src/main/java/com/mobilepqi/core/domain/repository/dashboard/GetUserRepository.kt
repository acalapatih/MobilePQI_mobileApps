package com.mobilepqi.core.domain.repository.dashboard

import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.domain.model.dashboard.GetUserModel
import kotlinx.coroutines.flow.Flow

interface GetUserRepository {
    fun getUser(): Flow<Resource<GetUserModel>>
}