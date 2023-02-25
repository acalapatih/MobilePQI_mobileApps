package com.mobilepqi.core.domain.repository.onboarding

interface OnboardingRepository {
    fun setShowOnboardingStatus(value: Boolean)
    fun getOnboardingStatus(): Boolean
    fun getToken(): String
}