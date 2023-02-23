package com.mobilepqi.core.data.source.local

import com.mobilepqi.core.data.source.local.sharedpref.MainPreferences

class LocalDataSource(private val sharedPref: MainPreferences) {

    fun setToken(token: String) {
        sharedPref.setToken(token)
    }

    fun getToken(): String {
        return sharedPref.getToken() ?: ""
    }

    fun clearToken() {
        return sharedPref.clearToken()
    }

    fun setOnboardingStatus(value: Boolean) {
        sharedPref.setOnboardingStatus(value)
    }

    fun getOnboardingStatus(): Boolean {
        return sharedPref.getOnboardingStatus()
    }

    fun clearOnboardingStatus() {
        return sharedPref.clearOnboardingStatus()
    }
}