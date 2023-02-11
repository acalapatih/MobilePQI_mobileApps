package com.mobilepqi.core.data.source.local

interface MainPreferences {
    fun setToken(token: String)
    fun getToken(): String?
    fun clearToken()
}