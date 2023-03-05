package com.mobilepqi.core.data.source.remote.response.buatkelas

import com.google.gson.annotations.SerializedName

data class BuatKelasResponse(

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

		@field:SerializedName("updated_at")
		val updatedAt: String? = null,

		@field:SerializedName("name")
		val name: String? = null,

		@field:SerializedName("created_at")
		val createdAt: String? = null,

		@field:SerializedName("ruang")
		val ruang: String? = null,

		@field:SerializedName("id")
		val id: Int? = null
	)
}
