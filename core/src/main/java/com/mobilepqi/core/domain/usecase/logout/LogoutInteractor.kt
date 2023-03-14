package com.mobilepqi.core.domain.usecase.logout

import com.mobilepqi.core.domain.repository.logout.LogoutRepository

class LogoutInteractor(
    private val repository: LogoutRepository
) : LogoutUseCase {
    override fun clearAllSharedPreferences() = repository.clearAllSharedPreferences()
}