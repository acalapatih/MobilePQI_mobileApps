package com.mobilepqi.core.di

import com.mobilepqi.core.BuildConfig
import com.mobilepqi.core.data.repository.jadwalsholat.JadwalSholatRepositoryImpl
import com.mobilepqi.core.data.repository.uploadimage.UploadImageRepositoryImpl
import com.mobilepqi.core.data.source.remote.RemoteDataSource
import com.mobilepqi.core.data.source.remote.network.ApiSholatService
import com.mobilepqi.core.data.source.remote.network.CommonService
import com.mobilepqi.core.domain.repository.jadwalsholat.JadwalSholatRepository
import com.mobilepqi.core.domain.repository.uploadimage.UploadImageRepository
import com.mobilepqi.core.util.HeaderInterceptor
import java.util.concurrent.TimeUnit
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single {
        val loggingInterceptor = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        } else {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
        }
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(HeaderInterceptor())
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
}

val repositoryModule = module {
    single { RemoteDataSource(get(), get()) }
    single<JadwalSholatRepository> { JadwalSholatRepositoryImpl(get()) }
    single<UploadImageRepository> { UploadImageRepositoryImpl(get()) }
}