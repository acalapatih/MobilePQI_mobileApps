package com.mobilepqi.core.data.source.local.sharedpref

import android.content.Context
import android.content.SharedPreferences

class MainPreferencesImpl(private val sharedPreferences: SharedPreferences) : MainPreferences {

    companion object {
        fun getInstances(context: Context): MainPreferences {
            val pref = context.getSharedPreferences(
                PREFERENCES_NAME,
                Context.MODE_PRIVATE
            )
            return MainPreferencesImpl(pref)
        }

        private const val PREFERENCES_NAME = "MOBILEPQI_SHARED_PREFERENCES"
        private const val PREF_TOKEN_AUTH = "PREF_TOKEN_AUTH"
        private const val PREF_ONBOARDING_STATUS = "PREF_ONBOARDING_STATUS"
        private const val PREF_USER_ROLE = "PREF_USER_ROLE"
        private const val PREF_CLASS_ID = "PREF_CLASS_ID"
    }

    override fun setToken(token: String) {
        sharedPreferences.edit().putString(PREF_TOKEN_AUTH, token).apply()
    }

    override fun getToken(): String? {
        return sharedPreferences.getString(PREF_TOKEN_AUTH, "")
    }

    override fun clearToken() {
        sharedPreferences.edit().remove(PREF_TOKEN_AUTH).apply()
    }

    override fun setOnboardingStatus(value: Boolean) {
        sharedPreferences.edit().putBoolean(PREF_ONBOARDING_STATUS, value).apply()
    }

    override fun getOnboardingStatus(): Boolean {
        return sharedPreferences.getBoolean(PREF_ONBOARDING_STATUS, true)
    }

    override fun clearOnboardingStatus() {
        sharedPreferences.edit().remove(PREF_ONBOARDING_STATUS).apply()
    }

    override fun setUserRole(role: String) {
        sharedPreferences.edit().putString(PREF_USER_ROLE, role).apply()
    }

    override fun getUserRole(): String? {
        return sharedPreferences.getString(PREF_USER_ROLE, "")
    }

    override fun clearUserRole() {
        sharedPreferences.edit().remove(PREF_USER_ROLE).apply()
    }

    override fun setClassId(classId: Int) {
        sharedPreferences.edit().putInt(PREF_CLASS_ID, classId).apply()
    }

    override fun getClassId(): Int {
        return sharedPreferences.getInt(PREF_CLASS_ID, 0)
    }

    override fun clearClassId() {
        sharedPreferences.edit().remove(PREF_CLASS_ID).apply()
    }
}