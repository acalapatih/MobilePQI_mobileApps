package com.mobilepqi.core.domain.usecase.onboarding

interface OnboardingUsecase {
    fun setShowOnboardingStatus(value: Boolean)
    fun getOnboardingStatus(): Boolean
    fun getToken(): String
}