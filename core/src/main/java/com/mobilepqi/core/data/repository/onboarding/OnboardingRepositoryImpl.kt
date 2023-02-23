package com.mobilepqi.core.data.repository.onboarding

import com.mobilepqi.core.data.source.local.LocalDataSource
import com.mobilepqi.core.domain.repository.onboarding.OnboardingRepository

class OnboardingRepositoryImpl(
    private val localDataSource: LocalDataSource
) : OnboardingRepository {

    override fun setShowOnboardingStatus(value: Boolean) =
        localDataSource.setOnboardingStatus(value)

    override fun getOnboardingStatus(): Boolean = localDataSource.getOnboardingStatus()
}