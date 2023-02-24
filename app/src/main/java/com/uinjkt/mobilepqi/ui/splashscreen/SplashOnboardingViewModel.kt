package com.uinjkt.mobilepqi.ui.splashscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mobilepqi.core.domain.usecase.onboarding.OnboardingUsecase

class SplashOnboardingViewModel(private val onboardingUsecase: OnboardingUsecase) : ViewModel() {

    private val _showOnboardingStatus = MutableLiveData<Boolean>()
    val showOnboardingStatus: LiveData<Boolean> get() = _showOnboardingStatus

    fun setShowOnboardingStatus(value: Boolean) {
        onboardingUsecase.setShowOnboardingStatus(value)
    }

    fun getShowOnboardingStatus() {
        _showOnboardingStatus.value = onboardingUsecase.getOnboardingStatus()
    }

}