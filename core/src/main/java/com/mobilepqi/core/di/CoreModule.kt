package com.mobilepqi.core.di

import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.mobilepqi.core.BuildConfig
import com.mobilepqi.core.data.repository.jadwalsholat.JadwalSholatRepositoryImpl
import com.mobilepqi.core.data.repository.profil.ProfilRepositoryImpl
import com.mobilepqi.core.data.repository.profil.PutProfilRepositoryImpl
import com.mobilepqi.core.data.repository.signin.SigninReposityImpl
import com.mobilepqi.core.data.repository.signin.SigninRepositoryImpl
import com.mobilepqi.core.data.repository.signup.SignupRepositoryImpl
import com.mobilepqi.core.data.repository.onboarding.OnboardingRepositoryImpl
import com.mobilepqi.core.data.repository.uploadimage.UploadFileOrImageRepositoryImpl
import com.mobilepqi.core.data.source.local.LocalDataSource
import com.mobilepqi.core.data.source.local.sharedpref.MainPreferencesImpl
import com.mobilepqi.core.data.source.remote.RemoteDataSource
import com.mobilepqi.core.data.source.remote.network.ApiSholatService
import com.mobilepqi.core.data.source.remote.network.CommonService
import com.mobilepqi.core.data.source.remote.network.MobilePqiService
import com.mobilepqi.core.domain.repository.jadwalsholat.JadwalSholatRepository
import com.mobilepqi.core.domain.repository.profil.ProfilRepository
import com.mobilepqi.core.domain.repository.profil.PutProfilRepository
import com.mobilepqi.core.domain.repository.signin.SigninRepository
import com.mobilepqi.core.domain.repository.signup.SignupRepository
import com.mobilepqi.core.domain.repository.onboarding.OnboardingRepository
import com.mobilepqi.core.domain.repository.upload.UploadFileOrImageRepository
import com.mobilepqi.core.util.HeaderInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val storageModule = module {
    single { MainPreferencesImpl.getInstances(androidContext()) }
}

val networkModule = module {
    single {
        val loggingInterceptor = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        } else {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
        }
        val chuckerInterceptor = ChuckerInterceptor.Builder(androidContext()).build()

        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(chuckerInterceptor)
            .addInterceptor(HeaderInterceptor(androidContext()))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.ENDPOINT_API_JADWAL_SHOLAT)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiSholatService::class.java)
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.ENDPOINT_API)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(CommonService::class.java)
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.ENDPOINT_API)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(MobilePqiService::class.java)
    }
}

val repositoryModule = module {
    single { RemoteDataSource(get(), get(), get()) }
    single { LocalDataSource(get()) }
    single<JadwalSholatRepository> { JadwalSholatRepositoryImpl(get()) }
    single<UploadFileOrImageRepository> { UploadFileOrImageRepositoryImpl(get()) }
    single<SigninRepository> { SigninRepositoryImpl(get(), get()) }
    single<SignupRepository> { SignupRepositoryImpl(get()) }
    single<OnboardingRepository> { OnboardingRepositoryImpl(get()) }
    single<SigninRepository> { SigninReposityImpl(get(), get()) }
    single<ProfilRepository> { ProfilRepositoryImpl(get()) }
    single<PutProfilRepository> { PutProfilRepositoryImpl(get()) }
}