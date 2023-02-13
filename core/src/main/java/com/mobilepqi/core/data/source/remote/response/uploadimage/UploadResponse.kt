package com.mobilepqi.core.data.source.remote.response.uploadimage

import com.google.gson.annotations.SerializedName

data class UploadResponse(

    @field:SerializedName("data")
	val data: Data? = null,

    @field:SerializedName("message")
	val message: String? = null,

    @field:SerializedName("status")
	val status: Int? = null
) {
	data class Data(

		@field:SerializedName("url")
		val url: String? = null,

		@field:SerializedName("errors")
		val error: String? = null
	)
}
