package com.mobilepqi.core.data.repository.logout

import com.mobilepqi.core.data.source.local.LocalDataSource
import com.mobilepqi.core.domain.repository.logout.LogoutRepository

class LogoutRepositoryImpl(
    private val localDataSource: LocalDataSource
): LogoutRepository {
    override fun clearAllSharedPreferences() {
        localDataSource.clearClassId()
        localDataSource.clearToken()
        localDataSource.clearUserRole()
    }
}