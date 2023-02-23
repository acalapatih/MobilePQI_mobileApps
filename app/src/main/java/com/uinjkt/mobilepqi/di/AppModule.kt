package com.uinjkt.mobilepqi.di

import com.mobilepqi.core.domain.usecase.jadwalsholat.JadwalSholatInteractor
import com.mobilepqi.core.domain.usecase.jadwalsholat.JadwalSholatUsecase
import com.mobilepqi.core.domain.usecase.profil.ProfilInteractor
import com.mobilepqi.core.domain.usecase.profil.ProfilUsecase
import com.mobilepqi.core.domain.usecase.signin.SigninIntercator
import com.mobilepqi.core.domain.usecase.signin.SigninUsecase
import com.mobilepqi.core.domain.usecase.upload.UploadFileOrImageInteractor
import com.mobilepqi.core.domain.usecase.upload.UploadFileOrImageUsecase
import com.uinjkt.mobilepqi.ui.dashboard.DashboardViewModel
import com.uinjkt.mobilepqi.ui.profil.ProfilViewModel
import com.uinjkt.mobilepqi.ui.signin.SignInViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<JadwalSholatUsecase> { JadwalSholatInteractor(get()) }
    factory<UploadFileOrImageUsecase> { UploadFileOrImageInteractor(get()) }
    factory<SigninUsecase> { SigninIntercator(get()) }
    factory<ProfilUsecase> { ProfilInteractor(get()) }
}

val viewModelModule = module {
    viewModel { DashboardViewModel(get(), get()) }
    viewModel { SignInViewModel(get()) }
    viewModel { ProfilViewModel(get()) }
}