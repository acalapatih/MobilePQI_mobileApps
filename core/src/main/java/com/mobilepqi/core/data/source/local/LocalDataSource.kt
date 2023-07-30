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
        sharedPref.clearToken()
    }

    fun setOnboardingStatus(value: Boolean) {
        sharedPref.setOnboardingStatus(value)
    }

    fun getOnboardingStatus(): Boolean {
        return sharedPref.getOnboardingStatus()
    }

    fun clearOnboardingStatus() {
        sharedPref.clearOnboardingStatus()
    }

    fun setUserRole(role: String) {
        sharedPref.setUserRole(role)
    }

    fun getUserRole(): String {
        return sharedPref.getUserRole() ?: ""
    }

    fun clearUserRole() {
        sharedPref.clearUserRole()
    }

    fun setClassId(classId: Int) {
        sharedPref.setClassId(classId)
    }

    fun getClassId(): Int {
        return sharedPref.getClassId()
    }

    fun clearClassId() {
        sharedPref.clearClassId()
    }
}