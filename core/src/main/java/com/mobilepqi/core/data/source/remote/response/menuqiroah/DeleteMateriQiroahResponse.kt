package com.mobilepqi.core.data.source.remote.response.menuqiroah

import com.google.gson.annotations.SerializedName

data class DeleteMateriQiroahResponse(

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


