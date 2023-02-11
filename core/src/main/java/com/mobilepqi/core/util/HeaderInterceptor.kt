package com.mobilepqi.core.util

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {

    private var token: String = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MSwibmltIjoiMDEyMzQ1Njc4OTEwMTExMjEzIiwicm9sZSI6ImRvc2VuIn0.QhUDGeaLUSnF5Ev9hxC1cHAHrdECTpJICzUg_Rg9nI8"

//    fun Token(token: String) {
//        this.token = token
//    }

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        if (request.header("No-Authentication") == null) {
            //val token = getTokenFromSharedPreference();
            //or use Token Function
            if (token.isNotEmpty()) {
                val finalToken = "Bearer $token"
                request = request.newBuilder()
                    .addHeader("Authorization", finalToken)
                    .build()
            }

        }

        return chain.proceed(request)
    }

}