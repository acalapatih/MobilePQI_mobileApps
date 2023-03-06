package com.uinjkt.mobilepqi.di

import com.mobilepqi.core.domain.usecase.buatkelas.BuatKelasIteractor
import com.mobilepqi.core.domain.usecase.buatkelas.BuatKelasUsecase
import com.mobilepqi.core.domain.usecase.daftarkelas.DaftarKelasInteractor
import com.mobilepqi.core.domain.usecase.daftarkelas.DaftarKelasUsecase
import com.mobilepqi.core.domain.usecase.detailkelas.DetailKelasInteractor
import com.mobilepqi.core.domain.usecase.detailkelas.DetailKelasUsecase
import com.mobilepqi.core.domain.usecase.jadwalsholat.JadwalSholatInteractor
import com.mobilepqi.core.domain.usecase.jadwalsholat.JadwalSholatUsecase
import com.mobilepqi.core.domain.usecase.lupapassword.LupaPasswordInteractor
import com.mobilepqi.core.domain.usecase.lupapassword.LupaPasswordUsecase
import com.mobilepqi.core.domain.usecase.menuqiroah.MenuQiroahInteractor
import com.mobilepqi.core.domain.usecase.menuqiroah.MenuQiroahUsecase
import com.mobilepqi.core.domain.usecase.onboarding.OnboardingInteractor
import com.mobilepqi.core.domain.usecase.onboarding.OnboardingUsecase
import com.mobilepqi.core.domain.usecase.profil.ProfilInteractor
import com.mobilepqi.core.domain.usecase.profil.ProfilUsecase
import com.mobilepqi.core.domain.usecase.profil.PutProfilInteractor
import com.mobilepqi.core.domain.usecase.profil.PutProfilUsecase
import com.mobilepqi.core.domain.usecase.signin.SigninInteractor
import com.mobilepqi.core.domain.usecase.signin.SigninUsecase
import com.mobilepqi.core.domain.usecase.signup.SignupInteractor
import com.mobilepqi.core.domain.usecase.signup.SignupUsecase
import com.mobilepqi.core.domain.usecase.tambahdosen.TambahDosenInteractor
import com.mobilepqi.core.domain.usecase.tambahdosen.TambahDosenUsecase
import com.mobilepqi.core.domain.usecase.silabus.SilabusInteractor
import com.mobilepqi.core.domain.usecase.silabus.SilabusUsecase
import com.mobilepqi.core.domain.usecase.upload.UploadFileOrImageInteractor
import com.mobilepqi.core.domain.usecase.upload.UploadFileOrImageUsecase
import com.uinjkt.mobilepqi.ui.dashboard.viewmodel.DashboardSharedViewModel
import com.uinjkt.mobilepqi.ui.dashboard.viewmodel.DashboardViewModel
import com.uinjkt.mobilepqi.ui.profil.ProfilViewModel
import com.uinjkt.mobilepqi.ui.kelas.buatkelas.BuatKelasViewModel
import com.uinjkt.mobilepqi.ui.kelas.daftarkelas.DaftarKelasViewModel
import com.uinjkt.mobilepqi.ui.kelas.detailkelas.DetailKelasViewModel
import com.uinjkt.mobilepqi.ui.kelas.tambahdosen.TambahDosenViewModel
import com.uinjkt.mobilepqi.ui.dosen.menuqiroah.DosenMateriDetailQiroahViewModel
import com.uinjkt.mobilepqi.ui.dosen.menuqiroah.DosenMateriQiroahViewModel
import com.uinjkt.mobilepqi.ui.dosen.menusilabus.DosenSilabusViewModel
import com.uinjkt.mobilepqi.ui.lupapassword.LupaPasswordViewModel
import com.uinjkt.mobilepqi.ui.mahasiswa.menuqiroah.MahasiswaMateriDetailQiroahViewModel
import com.uinjkt.mobilepqi.ui.mahasiswa.menuqiroah.MahasiswaMateriQiroahViewModel
import com.uinjkt.mobilepqi.ui.mahasiswa.menusilabus.MahasiswaSilabusViewModel
import com.uinjkt.mobilepqi.ui.signin.SigninViewModel
import com.uinjkt.mobilepqi.ui.signup.SignupViewModel
import com.uinjkt.mobilepqi.ui.splashscreen.SplashOnboardingViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<JadwalSholatUsecase> { JadwalSholatInteractor(get()) }
    factory<UploadFileOrImageUsecase> { UploadFileOrImageInteractor(get()) }
    factory<SigninUsecase> { SigninInteractor(get()) }
    factory<ProfilUsecase> { ProfilInteractor(get()) }
    factory<PutProfilUsecase> { PutProfilInteractor(get()) }
    factory<SigninUsecase> { SigninInteractor(get()) }
    factory<SignupUsecase> { SignupInteractor(get()) }
    factory<MenuQiroahUsecase> { MenuQiroahInteractor(get()) }
    factory<OnboardingUsecase> { OnboardingInteractor(get()) }
    factory<TambahDosenUsecase> { TambahDosenInteractor(get()) }
    factory<BuatKelasUsecase> { BuatKelasIteractor(get()) }
    factory<DaftarKelasUsecase> { DaftarKelasInteractor(get()) }
    factory<DetailKelasUsecase> { DetailKelasInteractor(get()) }
    factory<LupaPasswordUsecase> { LupaPasswordInteractor(get()) }
    factory<SilabusUsecase> { SilabusInteractor(get()) }
}

val viewModelModule = module {
    viewModel { DashboardViewModel(get(), get(), get()) }
    viewModel { SigninViewModel(get()) }
    viewModel { ProfilViewModel(get(), get()) }
    viewModel { SigninViewModel(get()) }
    viewModel { SignupViewModel(get()) }
    viewModel { DosenMateriQiroahViewModel(get()) }
    viewModel { DosenMateriDetailQiroahViewModel(get(), get()) }
    viewModel { SplashOnboardingViewModel(get()) }
    viewModel { DashboardSharedViewModel() }
    viewModel { TambahDosenViewModel(get()) }
    viewModel { BuatKelasViewModel(get()) }
    viewModel { DaftarKelasViewModel(get()) }
    viewModel { DetailKelasViewModel(get()) }
    viewModel { MahasiswaMateriQiroahViewModel(get()) }
    viewModel { MahasiswaMateriDetailQiroahViewModel(get()) }
    viewModel { LupaPasswordViewModel(get()) }
    viewModel { DosenSilabusViewModel(get(), get()) }
    viewModel { MahasiswaSilabusViewModel(get()) }
}