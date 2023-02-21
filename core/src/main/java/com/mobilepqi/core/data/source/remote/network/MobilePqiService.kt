package com.mobilepqi.core.data.source.remote.network

import com.mobilepqi.core.data.source.remote.response.signin.SigninPayload
import com.mobilepqi.core.data.source.remote.response.signin.SigninResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface MobilePqiService {

    @POST("v1/mobilepqi/users/login")
    suspend fun signin(@Body payload: SigninPayload): SigninResponse

}