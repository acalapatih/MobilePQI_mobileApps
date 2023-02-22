package com.uinjkt.mobilepqi.di

import com.mobilepqi.core.domain.usecase.jadwalsholat.JadwalSholatInteractor
import com.mobilepqi.core.domain.usecase.jadwalsholat.JadwalSholatUsecase
import com.mobilepqi.core.domain.usecase.signin.SigninInteractor
import com.mobilepqi.core.domain.usecase.signin.SigninUsecase
import com.mobilepqi.core.domain.usecase.upload.UploadFileOrImageInteractor
import com.mobilepqi.core.domain.usecase.upload.UploadFileOrImageUsecase
import com.uinjkt.mobilepqi.ui.dashboard.DashboardViewModel
import com.uinjkt.mobilepqi.ui.signin.SigninViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<JadwalSholatUsecase> { JadwalSholatInteractor(get()) }
    factory<UploadFileOrImageUsecase> { UploadFileOrImageInteractor(get()) }
    factory<SigninUsecase> { SigninInteractor(get()) }
}

val viewModelModule = module {
    viewModel { DashboardViewModel(get(), get()) }
    viewModel { SigninViewModel(get()) }
}