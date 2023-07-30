package com.mobilepqi.core.data.source.remote.response.tugas

import com.google.gson.annotations.SerializedName

data class CreateJawabanPayload(

	@field:SerializedName("file")
	val file: String? = null
)
