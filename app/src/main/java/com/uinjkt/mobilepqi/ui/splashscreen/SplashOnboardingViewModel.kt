package com.uinjkt.mobilepqi.ui.splashscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mobilepqi.core.domain.usecase.onboarding.OnboardingUsecase

class SplashOnboardingViewModel(
    private val onboardingUsecase: OnboardingUsecase
) : ViewModel() {

    private val _showOnboardingStatus = MutableLiveData<Boolean>()
    val showOnboardingStatus: LiveData<Boolean> get() = _showOnboardingStatus

    private val _token = MutableLiveData<String>()
    val token: LiveData<String> get() = _token

    private val _userRole = MutableLiveData<String>()
    val userRole: LiveData<String> get() = _userRole

    fun setShowOnboardingStatus(value: Boolean) {
        onboardingUsecase.setShowOnboardingStatus(value)
    }

    fun getShowOnboardingStatus() {
        _showOnboardingStatus.value = onboardingUsecase.getOnboardingStatus()
    }

    fun getToken() {
        _token.value = onboardingUsecase.getToken()
    }

    fun getUserRole() {
        _userRole.value = onboardingUsecase.getUserRole()
    }

}