package com.mobilepqi.core.data.source.remote.response.tugas

import com.google.gson.annotations.SerializedName

data class GetListTopicTugasResponse(

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Int? = null,
) {

    data class Data(

		@field:SerializedName("errors")
		val errors: String? = null,

		@field:SerializedName("tugas")
		val tugas: List<TugasItem?>? = null,

		@field:SerializedName("tugas_count")
		val tugasCount: Int? = null,

		@field:SerializedName("kelas_id")
		val kelasId: Int? = null,
	) {
        data class TugasItem(

			@field:SerializedName("id")
			val id: Int? = null,

			@field:SerializedName("title")
			val title: String? = null,

			@field:SerializedName("deadline")
			val deadline: String? = null,

			@field:SerializedName("status")
			val status: Boolean? = null,
		)
    }
}



