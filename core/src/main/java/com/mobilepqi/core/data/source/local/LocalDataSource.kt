package com.mobilepqi.core.data.source.local

import com.mobilepqi.core.data.source.local.sharedpref.MainPreferences

class LocalDataSource(private val sharedPref: MainPreferences) {

    fun setToken(token: String) {
        sharedPref.setToken(token)
    }

    fun getToken(): String {
        return sharedPref.getToken() ?: ""
    }
}