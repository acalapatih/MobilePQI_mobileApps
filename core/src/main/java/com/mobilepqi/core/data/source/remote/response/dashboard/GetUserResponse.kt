package com.mobilepqi.core.data.source.remote.response.dashboard

import com.google.gson.annotations.SerializedName

data class GetUserResponse(

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

		@field:SerializedName("nim")
		val nim: String? = null,

		@field:SerializedName("role")
		val role: String? = null,

		@field:SerializedName("name")
		val name: String? = null,

		@field:SerializedName("id")
		val id: Int? = null,

		@field:SerializedName("avatar")
		val avatar: String? = null
	)
}


