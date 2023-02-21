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
}