package com.mobilepqi.core.data.source.remote.response.menuqiroah

import com.google.gson.annotations.SerializedName

data class DeleteMateriQiroahResponse(

	@field:SerializedName("data")
	val data: Any? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Int? = null
)
