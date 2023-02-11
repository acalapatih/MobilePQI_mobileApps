package com.mobilepqi.core.data.source.remote.network

import com.mobilepqi.core.data.source.remote.response.uploadimage.UploadResponse
import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface CommonService {

    @Multipart
    @POST("v1/mobilepqi/common/upload")
    suspend fun uploadToServer(
        @Part file: MultipartBody.Part
    ): UploadResponse

}