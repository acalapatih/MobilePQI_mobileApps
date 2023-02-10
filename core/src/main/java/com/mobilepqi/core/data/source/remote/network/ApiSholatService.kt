package com.mobilepqi.core.data.source.remote.network

import com.mobilepqi.core.data.source.remote.response.JadwalSholatResponse
import java.sql.Timestamp
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiSholatService {
    @GET("v1/timings/{timestamp}")
    suspend fun getJadwalSholat(
        @Path("timestamp") timestamp: String = Timestamp(System.currentTimeMillis()).toString(),
        @Query("latitude") latitude: String,
        @Query("longitude") longitude: String,
        @Query("method") method: String = "11"
    ): JadwalSholatResponse
}