package com.uinjkt.mobilepqi.di

import com.mobilepqi.core.domain.usecase.buatkelas.BuatKelasIteractor
import com.mobilepqi.core.domain.usecase.buatkelas.BuatKelasUsecase
import com.mobilepqi.core.domain.usecase.daftarkelas.DaftarKelasInteractor
import com.mobilepqi.core.domain.usecase.daftarkelas.DaftarKelasUsecase
import com.mobilepqi.core.domain.usecase.dashboard.getClass.GetClassInteractor
import com.mobilepqi.core.domain.usecase.dashboard.getClass.GetClassUsecase
import com.mobilepqi.core.domain.usecase.dashboard.getTugas.GetTugasInteractor
import com.mobilepqi.core.domain.usecase.dashboard.getTugas.GetTugasUsecase
import com.mobilepqi.core.domain.usecase.dashboard.getUser.GetUserInteractor
import com.mobilepqi.core.domain.usecase.dashboard.getUser.GetUserUsecase
import com.mobilepqi.core.domain.usecase.detailkelas.DetailKelasInteractor
import com.mobilepqi.core.domain.usecase.detailkelas.DetailKelasUsecase
import com.mobilepqi.core.domain.usecase.ibadah.MenuIbadahInteractor
import com.mobilepqi.core.domain.usecase.ibadah.MenuIbadahUsecase
import com.mobilepqi.core.domain.usecase.jadwalsholat.JadwalSholatInteractor
import com.mobilepqi.core.domain.usecase.jadwalsholat.JadwalSholatUsecase
import com.mobilepqi.core.domain.usecase.logout.LogoutInteractor
import com.mobilepqi.core.domain.usecase.logout.LogoutUseCase
import com.mobilepqi.core.domain.usecase.lupapassword.LupaPasswordInteractor
import com.mobilepqi.core.domain.usecase.lupapassword.LupaPasswordUsecase
import com.mobilepqi.core.domain.usecase.onboarding.OnboardingInteractor
import com.mobilepqi.core.domain.usecase.onboarding.OnboardingUsecase
import com.mobilepqi.core.domain.usecase.profil.ProfilInteractor
import com.mobilepqi.core.domain.usecase.profil.ProfilUsecase
import com.mobilepqi.core.domain.usecase.profil.PutProfilInteractor
import com.mobilepqi.core.domain.usecase.profil.PutProfilUsecase
import com.mobilepqi.core.domain.usecase.qiroah.MenuQiroahInteractor
import com.mobilepqi.core.domain.usecase.qiroah.MenuQiroahUsecase
import com.mobilepqi.core.domain.usecase.signin.SigninInteractor
import com.mobilepqi.core.domain.usecase.signin.SigninUsecase
import com.mobilepqi.core.domain.usecase.signup.SignupInteractor
import com.mobilepqi.core.domain.usecase.signup.SignupUsecase
import com.mobilepqi.core.domain.usecase.silabus.SilabusInteractor
import com.mobilepqi.core.domain.usecase.silabus.SilabusUsecase
import com.mobilepqi.core.domain.usecase.tambahdosen.TambahDosenInteractor
import com.mobilepqi.core.domain.usecase.tambahdosen.TambahDosenUsecase
import com.mobilepqi.core.domain.usecase.tugas.MenuTugasInteractor
import com.mobilepqi.core.domain.usecase.tugas.MenuTugasUseCase
import com.mobilepqi.core.domain.usecase.upload.UploadFileOrImageInteractor
import com.mobilepqi.core.domain.usecase.upload.UploadFileOrImageUsecase
import com.uinjkt.mobilepqi.ui.dashboard.viewmodel.DashboardSharedViewModel
import com.uinjkt.mobilepqi.ui.dashboard.viewmodel.DashboardViewModel
import com.uinjkt.mobilepqi.ui.dosen.menuibadah.DosenMateriDetailIbadahViewModel
import com.uinjkt.mobilepqi.ui.dosen.menuibadah.DosenMateriIbadahViewModel
import com.uinjkt.mobilepqi.ui.dosen.menuqiroah.DosenMateriDetailQiroahViewModel
import com.uinjkt.mobilepqi.ui.dosen.menuqiroah.DosenMateriQiroahViewModel
import com.uinjkt.mobilepqi.ui.dosen.menusilabus.DosenSilabusViewModel
import com.uinjkt.mobilepqi.ui.dosen.menutugas.DosenBeriNilaiTugasMahasiswaViewModel
import com.uinjkt.mobilepqi.ui.dosen.menutugas.DosenBuatEditTugasViewModel
import com.uinjkt.mobilepqi.ui.dosen.menutugas.DosenCekTugasMahasiswaViewModel
import com.uinjkt.mobilepqi.ui.dosen.menutugas.DosenDetailTugasViewModel
import com.uinjkt.mobilepqi.ui.dosen.menutugas.DosenTugasFilterViewModel
import com.uinjkt.mobilepqi.ui.dosen.menutugas.DosenTugasViewModel
import com.uinjkt.mobilepqi.ui.kelas.buatkelas.BuatKelasViewModel
import com.uinjkt.mobilepqi.ui.kelas.daftarkelas.DaftarKelasViewModel
import com.uinjkt.mobilepqi.ui.kelas.detailkelas.DetailKelasViewModel
import com.uinjkt.mobilepqi.ui.kelas.tambahdosen.TambahDosenViewModel
import com.uinjkt.mobilepqi.ui.lupapassword.LupaPasswordViewModel
import com.uinjkt.mobilepqi.ui.mahasiswa.menuibadah.MahasiswaMateriDetailIbadahViewModel
import com.uinjkt.mobilepqi.ui.mahasiswa.menuibadah.MahasiswaMateriIbadahViewModel
import com.uinjkt.mobilepqi.ui.mahasiswa.menuqiroah.MahasiswaMateriDetailQiroahViewModel
import com.uinjkt.mobilepqi.ui.mahasiswa.menuqiroah.MahasiswaMateriQiroahViewModel
import com.uinjkt.mobilepqi.ui.mahasiswa.menusilabus.MahasiswaSilabusViewModel
import com.uinjkt.mobilepqi.ui.mahasiswa.menutugas.MahasiswaDetailTugasViewModel
import com.uinjkt.mobilepqi.ui.mahasiswa.menutugas.MahasiswaTugasFilterViewModel
import com.uinjkt.mobilepqi.ui.mahasiswa.menutugas.MahasiswaTugasViewModel
import com.uinjkt.mobilepqi.ui.profil.ProfilViewModel
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
    factory<MenuIbadahUsecase> { MenuIbadahInteractor(get()) }
    factory<OnboardingUsecase> { OnboardingInteractor(get()) }
    factory<TambahDosenUsecase> { TambahDosenInteractor(get()) }
    factory<BuatKelasUsecase> { BuatKelasIteractor(get()) }
    factory<DaftarKelasUsecase> { DaftarKelasInteractor(get()) }
    factory<DetailKelasUsecase> { DetailKelasInteractor(get()) }
    factory<LupaPasswordUsecase> { LupaPasswordInteractor(get()) }
    factory<SilabusUsecase> { SilabusInteractor(get()) }
    factory<MenuTugasUseCase> { MenuTugasInteractor(get()) }
    factory<GetTugasUsecase> { GetTugasInteractor(get()) }
    factory<GetClassUsecase> { GetClassInteractor(get()) }
    factory<GetUserUsecase> { GetUserInteractor(get()) }
    factory<LogoutUseCase> { LogoutInteractor(get()) }
}

val viewModelModule = module {
    viewModel { DashboardViewModel(get(), get(), get(), get(), get(), get()) }
    viewModel { SigninViewModel(get()) }
    viewModel { ProfilViewModel(get(), get(), get(), get()) }
    viewModel { SigninViewModel(get()) }
    viewModel { SignupViewModel(get()) }
    viewModel { DosenMateriQiroahViewModel(get()) }
    viewModel { DosenMateriDetailQiroahViewModel(get(), get()) }
    viewModel { DosenMateriIbadahViewModel(get()) }
    viewModel { DosenMateriDetailIbadahViewModel(get(), get()) }
    viewModel { SplashOnboardingViewModel(get()) }
    viewModel { DashboardSharedViewModel() }
    viewModel { TambahDosenViewModel(get()) }
    viewModel { BuatKelasViewModel(get()) }
    viewModel { DaftarKelasViewModel(get()) }
    viewModel { DetailKelasViewModel(get()) }
    viewModel { MahasiswaMateriQiroahViewModel(get()) }
    viewModel { MahasiswaMateriDetailQiroahViewModel(get()) }
    viewModel { MahasiswaMateriIbadahViewModel(get()) }
    viewModel { MahasiswaMateriDetailIbadahViewModel(get()) }
    viewModel { LupaPasswordViewModel(get()) }
    viewModel { DosenSilabusViewModel(get(), get()) }
    viewModel { MahasiswaSilabusViewModel(get()) }
    viewModel { DosenTugasViewModel(get()) }
    viewModel { DosenBuatEditTugasViewModel(get(), get()) }
    viewModel { DosenTugasFilterViewModel(get()) }
    viewModel { DosenDetailTugasViewModel(get()) }
    viewModel { DosenCekTugasMahasiswaViewModel(get()) }
    viewModel { DosenBeriNilaiTugasMahasiswaViewModel(get()) }
    viewModel { MahasiswaTugasViewModel(get()) }
    viewModel { MahasiswaTugasFilterViewModel(get()) }
    viewModel { MahasiswaDetailTugasViewModel(get(), get()) }
}