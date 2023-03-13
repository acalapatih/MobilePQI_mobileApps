package com.mobilepqi.core.data.source.remote.response.dashboard

import com.google.gson.annotations.SerializedName

data class GetClassResponse(

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Int? = null
) {
	data class Data(

		@field:SerializedName("errors")
		val errors: String? = null,

		@field:SerializedName("jadwal")
		val jadwal: String? = null,

		@field:SerializedName("code")
		val code: String? = null,

		@field:SerializedName("name")
		val name: String? = null,

		@field:SerializedName("ruang")
		val ruang: String? = null,

		@field:SerializedName("id")
		val id: Int? = null
	)
}
