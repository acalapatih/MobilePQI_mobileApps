package com.uinjkt.mobilepqi.di

import com.mobilepqi.core.domain.usecase.JadwalSholatInteractor
import com.mobilepqi.core.domain.usecase.JadwalSholatUsecase
import com.uinjkt.mobilepqi.ui.dashboard.DashboardViewModel
import com.uinjkt.mobilepqi.ui.signin.SignInViewModel
import com.uinjkt.mobilepqi.ui.signin.SigninActivity
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<JadwalSholatUsecase> { JadwalSholatInteractor(get()) }
}

val viewModelModule = module {
    viewModel { DashboardViewModel(get()) }
    viewModel { SignInViewModel() }
}