package com.mobilepqi.core.data.source.remote.response.qiroah

import com.google.gson.annotations.SerializedName

data class UpdateDetailMateriQiroahPayload(

	@field:SerializedName("file")
	val file: List<FileItem?>? = null,

	@field:SerializedName("description")
	val description: String? = null
) {
	data class FileItem(

		@field:SerializedName("url")
		val url: String? = null
	)
}
