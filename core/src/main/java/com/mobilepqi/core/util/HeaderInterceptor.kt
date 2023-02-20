package com.mobilepqi.core.util

import android.content.Context
import com.mobilepqi.core.data.source.local.sharedpref.MainPreferences
import com.mobilepqi.core.data.source.local.sharedpref.MainPreferencesImpl
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor(private val context: Context) : Interceptor {

    private lateinit var mainPreferences: MainPreferences

    private fun getMainPreferences(): MainPreferences {
        if (!::mainPreferences.isInitialized) {
            mainPreferences = MainPreferencesImpl.getInstances(context)
        }

        return mainPreferences
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        if (request.header("No-Authentication") == null) {
            if (getToken().isNotEmpty()) {
                val finalToken = "Bearer ${getToken()}"
                request = request.newBuilder()
                    .addHeader("Authorization", finalToken)
                    .build()
            }
        }

        return chain.proceed(request)
    }

    private fun getToken(): String {
        return if (getMainPreferences().getToken() != null) {
            getMainPreferences().getToken()!!
        } else {
            ""
        }
    }

}