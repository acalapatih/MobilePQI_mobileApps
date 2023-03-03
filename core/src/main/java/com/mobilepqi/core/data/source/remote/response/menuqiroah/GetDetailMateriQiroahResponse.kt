package com.mobilepqi.core.data.source.remote.response.menuqiroah

import com.google.gson.annotations.SerializedName

data class GetDetailMateriQiroahResponse(

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

		@field:SerializedName("file")
		val file: List<FileItem?>? = null,

		@field:SerializedName("updated_at")
		val updatedAt: String? = null,

		@field:SerializedName("description")
		val description: String? = null,

		@field:SerializedName("created_at")
		val createdAt: String? = null,

		@field:SerializedName("id")
		val id: Int? = null,

		@field:SerializedName("type")
		val type: String? = null,

		@field:SerializedName("title")
		val title: String? = null,

		@field:SerializedName("kelas_id")
		val kelasId: Int? = null,

		@field:SerializedName("created_by")
		val createdBy: String? = null
	) {
		data class FileItem(
			@field:SerializedName("url")
			val url: String? = null
		)
	}
}




