package com.mobilepqi.core.data.source.local.sharedpref

interface MainPreferences {
    fun setToken(token: String)
    fun getToken(): String?
    fun clearToken()
}