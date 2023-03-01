package com.mobilepqi.core.data.source.remote.network

import com.mobilepqi.core.data.source.remote.response.buatkelas.BuatKelasPayload
import com.mobilepqi.core.data.source.remote.response.buatkelas.BuatKelasResponse
import com.mobilepqi.core.data.source.remote.response.daftarkelas.DaftarKelasResponse
import com.mobilepqi.core.data.source.remote.response.detailkelas.DetailKelasResponse
import com.mobilepqi.core.data.source.remote.response.signin.SigninPayload
import com.mobilepqi.core.data.source.remote.response.signin.SigninResponse
import com.mobilepqi.core.data.source.remote.response.signup.SignupPayload
import com.mobilepqi.core.data.source.remote.response.signup.SignupResponse
import com.mobilepqi.core.data.source.remote.response.tambahdosen.TambahDosenResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface MobilePqiService {

    @POST("v1/mobilepqi/users/login")
    suspend fun signin(@Body payload: SigninPayload): SigninResponse

    @POST("v1/mobilepqi/users")
    suspend fun signup(@Body payload: SignupPayload): SignupResponse

    @GET("v1/mobilepqi/kelas/1/dosen")
    suspend fun tambahdosen(): TambahDosenResponse

    @POST("v1/mobilepqi/kelas")
    suspend fun buatkelas(@Body payload: BuatKelasPayload): BuatKelasResponse

    @GET("v1/mobilepqi/kelas")
    suspend fun daftarkelas(): DaftarKelasResponse

    @GET("v1/mobilepqi/kelas/1")
    suspend fun detailkelas(): DetailKelasResponse
}