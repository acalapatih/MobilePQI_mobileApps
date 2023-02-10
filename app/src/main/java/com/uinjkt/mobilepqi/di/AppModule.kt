package com.uinjkt.mobilepqi.di

import com.mobilepqi.core.domain.usecase.jadwalsholat.JadwalSholatInteractor
import com.mobilepqi.core.domain.usecase.jadwalsholat.JadwalSholatUsecase
import com.mobilepqi.core.domain.usecase.uploadimage.UploadImageInteractor
import com.mobilepqi.core.domain.usecase.uploadimage.UploadImageUsecase
import com.uinjkt.mobilepqi.ui.dashboard.DashboardViewModel
import com.uinjkt.mobilepqi.ui.signin.SignInViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<JadwalSholatUsecase> { JadwalSholatInteractor(get()) }
    factory<UploadImageUsecase> { UploadImageInteractor(get()) }
}

val viewModelModule = module {
    viewModel { DashboardViewModel(get(), get()) }
    viewModel { SignInViewModel() }
}