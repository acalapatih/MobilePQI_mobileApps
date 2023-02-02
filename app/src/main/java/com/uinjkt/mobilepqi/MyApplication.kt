package com.uinjkt.mobilepqi

import android.app.Application
import com.mobilepqi.core.di.networkModule
import com.mobilepqi.core.di.repositoryModule
import com.uinjkt.mobilepqi.di.useCaseModule
import com.uinjkt.mobilepqi.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}