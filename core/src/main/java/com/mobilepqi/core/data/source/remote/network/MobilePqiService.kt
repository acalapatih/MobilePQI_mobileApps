package com.mobilepqi.core.data.source.remote.network

import com.mobilepqi.core.data.source.remote.response.buatkelas.BuatKelasPayload
import com.mobilepqi.core.data.source.remote.response.buatkelas.BuatKelasResponse
import com.mobilepqi.core.data.source.remote.response.daftarkelas.DaftarKelasResponse
import com.mobilepqi.core.data.source.remote.response.detailkelas.DetailKelasResponse
import com.mobilepqi.core.data.source.remote.response.lupapassword.LupaPasswordPayload
import com.mobilepqi.core.data.source.remote.response.lupapassword.LupaPasswordResponse
import com.mobilepqi.core.data.source.remote.response.menuqiroah.*
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
import com.mobilepqi.core.data.source.remote.response.silabus.CreateSilabusPayload
import com.mobilepqi.core.data.source.remote.response.silabus.CreateSilabusResponse
import com.mobilepqi.core.data.source.remote.response.silabus.DeleteSilabusResponse
import com.mobilepqi.core.data.source.remote.response.silabus.GetSilabusResponse
import retrofit2.http.*

interface MobilePqiService {

    @POST("v1/mobilepqi/users/login")
    suspend fun signin(@Body payload: SigninPayload): SigninResponse

    @POST("v1/mobilepqi/users")
    suspend fun signup(@Body payload: SignupPayload): SignupResponse

    @POST("v1/mobilepqi/users/password")
    suspend fun lupaPassword(@Body payload: LupaPasswordPayload): LupaPasswordResponse

    @POST("v1/mobilepqi/kelas/{idKelas}/materi/qiroah")
    suspend fun createMateriQiroah(
        @Body payload: CreateMateriQiroahPayload,
        @Path("idKelas") idKelas: Int
    ): CreateMateriQiroahResponse

    @GET("v1/mobilepqi/kelas/{idKelas}/materi/qiroah")
    suspend fun getMateriQiroah(
        @Path("idKelas") idKelas: Int
    ): GetMateriQiroahResponse

    @GET("v1/mobilepqi/kelas/materi/{idMateri}")
    suspend fun getDetailMateriQiroah(
        @Path("idMateri") idMateri: Int
    ): GetDetailMateriQiroahResponse

    @PUT("v1/mobilepqi/kelas/materi/{idMateri}")
    suspend fun updateDetailMateriQiroah(
        @Body payload: UpdateDetailMateriQiroahPayload,
        @Path("idMateri") idMateri: Int
    ): UpdateDetailMateriQiroahResponse

    @DELETE("v1/mobilepqi/kelas/materi/{idMateri}")
    suspend fun deleteMateriQiroah(
        @Path("idMateri") idMateri: Int
    ): DeleteMateriQiroahResponse

    @POST("v1/mobilepqi/kelas/{idKelas}/silabus")
    suspend fun createSilabus(
        @Body payload: CreateSilabusPayload,
        @Path("idKelas") idKelas: Int
    ): CreateSilabusResponse

    @GET("v1/mobilepqi/kelas/{idKelas}/silabus")
    suspend fun getSilabus(
        @Path("idKelas") idKelas: Int
    ): GetSilabusResponse

    @DELETE("v1/mobilepqi/kelas/{idKelas}/silabus")
    suspend fun deleteSilabus(
        @Path("idKelas") idKelas: Int
    ): DeleteSilabusResponse
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