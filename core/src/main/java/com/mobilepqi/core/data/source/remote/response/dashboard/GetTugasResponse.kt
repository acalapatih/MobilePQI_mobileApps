package com.mobilepqi.core.data.source.remote.response.dashboard

import com.google.gson.annotations.SerializedName

data class GetTugasResponse(

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

		@field:SerializedName("tugas_count")
		val tugasCount: Int? = null,

		@field:SerializedName("hafalan")
		val hafalan: List<HafalanItem?>? = null,

		@field:SerializedName("kelas_id")
		val kelasId: Int? = null
	)

	data class HafalanItem(

		@field:SerializedName("errors")
		val errors: String? = null,

		@field:SerializedName("created_at")
		val createdAt: String? = null,

		@field:SerializedName("id")
		val id: Int? = null,

		@field:SerializedName("title")
		val title: String? = null,

		@field:SerializedName("deadline")
		val deadline: String? = null,

		@field:SerializedName("type")
		val type: String? = null,

		@field:SerializedName("status")
		val status: Boolean? = null
	)
}
