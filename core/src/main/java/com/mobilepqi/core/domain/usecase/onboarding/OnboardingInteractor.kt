package com.mobilepqi.core.domain.usecase.onboarding

import com.mobilepqi.core.domain.repository.onboarding.OnboardingRepository

class OnboardingInteractor(private val repository: OnboardingRepository): OnboardingUsecase {
    override fun setShowOnboardingStatus(value: Boolean) = repository.setShowOnboardingStatus(value)

    override fun getOnboardingStatus(): Boolean = repository.getOnboardingStatus()
    override fun getToken(): String = repository.getToken()
    override fun getUserRole(): String = repository.getUserRole()
    override fun getClassId(): Int = repository.getClassId()

}