package com.mobilepqi.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class CreateNilaiResponse(

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

		@field:SerializedName("tugas_id")
		val tugasId: Int? = null,

		@field:SerializedName("nim")
		val nim: String? = null,

		@field:SerializedName("file")
		val file: String? = null,

		@field:SerializedName("updated_at")
		val updatedAt: String? = null,

		@field:SerializedName("nilai")
		val nilai: Int? = null,

		@field:SerializedName("created_at")
		val createdAt: String? = null,

		@field:SerializedName("id")
		val id: Int? = null
	)
}


