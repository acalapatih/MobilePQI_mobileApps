package com.uinjkt.mobilepqi.di

import com.mobilepqi.core.domain.usecase.jadwalsholat.JadwalSholatInteractor
import com.mobilepqi.core.domain.usecase.jadwalsholat.JadwalSholatUsecase
import com.mobilepqi.core.domain.usecase.onboarding.OnboardingInteractor
import com.mobilepqi.core.domain.usecase.onboarding.OnboardingUsecase
import com.mobilepqi.core.domain.usecase.signin.SigninInteractor
import com.mobilepqi.core.domain.usecase.signin.SigninUsecase
import com.mobilepqi.core.domain.usecase.signup.SignupInteractor
import com.mobilepqi.core.domain.usecase.signup.SignupUsecase
import com.mobilepqi.core.domain.usecase.upload.UploadFileOrImageInteractor
import com.mobilepqi.core.domain.usecase.upload.UploadFileOrImageUsecase
import com.uinjkt.mobilepqi.ui.dashboard.viewmodel.DashboardSharedViewModel
import com.uinjkt.mobilepqi.ui.dashboard.viewmodel.DashboardViewModel
import com.uinjkt.mobilepqi.ui.signin.SigninViewModel
import com.uinjkt.mobilepqi.ui.signup.SignupViewModel
import com.uinjkt.mobilepqi.ui.splashscreen.SplashOnboardingViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<JadwalSholatUsecase> { JadwalSholatInteractor(get()) }
    factory<UploadFileOrImageUsecase> { UploadFileOrImageInteractor(get()) }
    factory<SigninUsecase> { SigninInteractor(get()) }
    factory<SignupUsecase> { SignupInteractor(get()) }
    factory<OnboardingUsecase> { OnboardingInteractor(get()) }
}

val viewModelModule = module {
    viewModel { DashboardViewModel(get(), get()) }
    viewModel { SigninViewModel(get()) }
    viewModel { SignupViewModel(get()) }
    viewModel { SplashOnboardingViewModel(get()) }
    viewModel { DashboardSharedViewModel() }
}