package com.mobilepqi.core.data.source.remote.response.dashboard

import com.google.gson.annotations.SerializedName

data class Data(

	@field:SerializedName("tugas_count")
	val tugasCount: Int? = null,

	@field:SerializedName("hafalan")
	val hafalan: Hafalan? = null,

	@field:SerializedName("kelas_id")
	val kelasId: Int? = null,

	@field:SerializedName("praktikum")
	val praktikum: Praktikum? = null
) {
	data class Hafalan(

		@field:SerializedName("errors")
		val errors: String? = null,

		@field:SerializedName("id")
		val id: Int? = null,

		@field:SerializedName("title")
		val title: String? = null,

		@field:SerializedName("deadline")
		val deadline: String? = null,

		@field:SerializedName("status")
		val status: Boolean? = null
	)

	data class Praktikum(

		@field:SerializedName("errors")
		val errors: String? = null,

		@field:SerializedName("id")
		val id: Int? = null,

		@field:SerializedName("title")
		val title: String? = null,

		@field:SerializedName("deadline")
		val deadline: String? = null,

		@field:SerializedName("status")
		val status: Boolean? = null
	)

	data class GetTugasResponse(

		@field:SerializedName("errors")
		val errors: String? = null,

		@field:SerializedName("data")
		val data: Data? = null,

		@field:SerializedName("message")
		val message: String? = null,

		@field:SerializedName("status")
		val status: Int? = null
	)
}
