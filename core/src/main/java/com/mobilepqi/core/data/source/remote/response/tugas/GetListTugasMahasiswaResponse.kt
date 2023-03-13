package com.mobilepqi.core.data.source.remote.response.tugas

import com.google.gson.annotations.SerializedName

data class GetListTugasMahasiswaResponse(

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

		@field:SerializedName("count")
		val count: Int? = null,

		@field:SerializedName("jawaban")
		val jawaban: List<JawabanItem?>? = null
	) {
		data class JawabanItem(

			@field:SerializedName("nim")
			val nim: String? = null,

			@field:SerializedName("name")
			val name: String? = null,

			@field:SerializedName("avatar")
			val avatar: String? = null,

			@field:SerializedName("status")
			val status: Boolean? = null
		)
	}
}




