package com.mobilepqi.core.data.source.remote.network

import com.mobilepqi.core.data.source.remote.response.buatkelas.BuatKelasPayload
import com.mobilepqi.core.data.source.remote.response.buatkelas.BuatKelasResponse
import com.mobilepqi.core.data.source.remote.response.daftarkelas.DaftarKelasResponse
import com.mobilepqi.core.data.source.remote.response.detailkelas.DetailKelasResponse
import com.mobilepqi.core.data.source.remote.response.signin.SigninPayload
import com.mobilepqi.core.data.source.remote.response.signin.SigninResponse
import com.mobilepqi.core.data.source.remote.response.signup.SignupPayload
import com.mobilepqi.core.data.source.remote.response.signup.SignupResponse
import com.mobilepqi.core.data.source.remote.response.tambahdosen.GetTambahDosenResponse
import com.mobilepqi.core.data.source.remote.response.tambahdosen.PostTambahDosenPayload
import com.mobilepqi.core.data.source.remote.response.tambahdosen.PostTambahDosenResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface MobilePqiService {

    @POST("v1/mobilepqi/users/login")
    suspend fun signin(@Body payload: SigninPayload): SigninResponse

    @POST("v1/mobilepqi/users")
    suspend fun signup(@Body payload: SignupPayload): SignupResponse

    @GET("v1/mobilepqi/kelas/{idKelas}/dosen")
    suspend fun getTambahDosen(@Path("idKelas")idKelas: Int): GetTambahDosenResponse

    @POST("v1/mobilepqi/kelas/{idKelas}/dosen")
    suspend fun postTambahdosen(@Body payload: PostTambahDosenPayload, @Path("idKelas")idKelas: Int): PostTambahDosenResponse

    @POST("v1/mobilepqi/kelas")
    suspend fun buatkelas(@Body payload: BuatKelasPayload): BuatKelasResponse

    @GET("v1/mobilepqi/kelas")
    suspend fun daftarkelas(): DaftarKelasResponse

    @GET("v1/mobilepqi/kelas/{idKelas}")
    suspend fun detailkelas(@Path("idKelas")idKelas: Int): DetailKelasResponse
}