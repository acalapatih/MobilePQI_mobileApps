package com.mobilepqi.core.data.source.remote.network

import com.mobilepqi.core.data.source.remote.response.profil.ProfilResponse
import com.mobilepqi.core.data.source.remote.response.profil.PutProfilPayload
import com.mobilepqi.core.data.source.remote.response.profil.PutProfilResponse
import com.mobilepqi.core.data.source.remote.response.signin.SigninPayload
import com.mobilepqi.core.data.source.remote.response.signin.SigninResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

interface MobilePqiService {

    @POST("v1/mobilepqi/users/login")
    suspend fun signin(@Body payload: SigninPayload): SigninResponse

    @GET("v1/mobilepqi/users")
    suspend fun profil(): ProfilResponse

    @PUT("v1/mobilepqi/users")
    suspend fun putprofil(@Body payload: PutProfilPayload): PutProfilResponse
}