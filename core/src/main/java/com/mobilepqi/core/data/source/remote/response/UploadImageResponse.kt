package com.mobilepqi.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class UploadImageResponse(

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Int? = null
) {
	data class Data(

		@field:SerializedName("avatar")
		val avatar: String? = null,

		@field:SerializedName("errors")
		val error: String? = null
	)
}
