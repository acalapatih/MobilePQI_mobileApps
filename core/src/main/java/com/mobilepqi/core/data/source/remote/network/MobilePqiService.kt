package com.mobilepqi.core.data.source.remote.network

import com.mobilepqi.core.data.source.remote.response.menuqiroah.*
import com.mobilepqi.core.data.source.remote.response.lupapassword.LupaPasswordPayload
import com.mobilepqi.core.data.source.remote.response.lupapassword.LupaPasswordResponse
import com.mobilepqi.core.data.source.remote.response.signin.SigninPayload
import com.mobilepqi.core.data.source.remote.response.signin.SigninResponse
import com.mobilepqi.core.data.source.remote.response.signup.SignupPayload
import com.mobilepqi.core.data.source.remote.response.signup.SignupResponse
import retrofit2.http.*

interface MobilePqiService {

    @POST("v1/mobilepqi/users/login")
    suspend fun signin(@Body payload: SigninPayload): SigninResponse

    @POST("v1/mobilepqi/users")
    suspend fun signup(@Body payload: SignupPayload): SignupResponse

    @POST("v1/mobilepqi/users/password")
    suspend fun lupaPassword(@Body payload: LupaPasswordPayload): LupaPasswordResponse

    @POST("v1/mobilepqi/kelas/1/materi/qiroah")
    suspend fun createMateriQiroah(@Body payload: CreateMateriQiroahPayload) : CreateMateriQiroahResponse

    @GET("v1/mobilepqi/kelas/1/materi/qiroah")
    suspend fun getMateriQiroah() : GetMateriQiroahResponse

    @GET("v1/mobilepqi/kelas/materi/{idMateri}")
    suspend fun getDetailMateriQiroah(
        @Path("idMateri") idMateri: Int
    ) : GetDetailMateriQiroahResponse
}