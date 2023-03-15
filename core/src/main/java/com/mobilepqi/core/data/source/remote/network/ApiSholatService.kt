package com.mobilepqi.core.data.source.remote.network

import com.mobilepqi.core.data.source.remote.response.jadwalsholat.JadwalSholatResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiSholatService {
    @GET("v1/timings/{timestamp}")
    suspend fun getJadwalSholat(
        @Path("timestamp") timestamp: String,
        @Query("latitude") latitude: String,
        @Query("longitude") longitude: String,
        @Query("method") method: String = "11"
    ): JadwalSholatResponse
}