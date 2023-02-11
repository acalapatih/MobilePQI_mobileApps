package com.mobilepqi.core.data.source.remote.network

import com.mobilepqi.core.data.source.remote.response.uploadimage.UploadImageResponse
import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface CommonService {

    @Multipart
    @POST("v1/mobilepqi/users/avatar")
    suspend fun uploadImage(
        @Part file: MultipartBody.Part
    ): UploadImageResponse

}