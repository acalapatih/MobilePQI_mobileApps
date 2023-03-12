package com.mobilepqi.core.data.source.remote.response.tugas

import com.google.gson.annotations.SerializedName

data class CreateNilaiPayload(
	@field:SerializedName("nilai")
	val nilai: Int? = null
)
