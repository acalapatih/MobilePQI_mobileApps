package com.mobilepqi.core.data.source.remote.response.menuqiroah

import com.google.gson.annotations.SerializedName

data class GetMateriQiroahResponse(

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Int? = null
) {
	data class Data(

		@field:SerializedName("errors")
		val error: String? = null,

		@field:SerializedName("materi")
		val materi: List<MateriItem?>? = null,

		@field:SerializedName("materi_count")
		val materiCount: Int? = null,

		@field:SerializedName("type")
		val type: String? = null,

		@field:SerializedName("kelas_id")
		val kelasId: Int? = null
	) {
		data class MateriItem(

			@field:SerializedName("id")
			val id: Int? = null,

			@field:SerializedName("title")
			val title: String? = null
		)

	}

}


