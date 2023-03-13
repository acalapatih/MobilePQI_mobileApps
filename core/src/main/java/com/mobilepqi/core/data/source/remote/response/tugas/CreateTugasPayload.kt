package com.mobilepqi.core.data.source.remote.response.tugas

import com.google.gson.annotations.SerializedName

data class CreateTugasPayload(

	@field:SerializedName("file")
	val file: List<FileItem?>? = null,

	@field:SerializedName("jenis")
	val jenis: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("topic")
	val topic: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("deadline")
	val deadline: String? = null
) {

	data class FileItem(

		@field:SerializedName("url")
		val url: String? = null
	)
}

