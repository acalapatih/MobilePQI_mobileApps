package com.mobilepqi.core.data.source.remote.response.tambahdosen

import com.google.gson.annotations.SerializedName

data class PostTambahDosenResponse(

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Int? = null
) {
	data class Data(

		@field:SerializedName("errors")
		val errors: String? = null
	)
}
