package com.mobilepqi.core.data.source.remote.response.buatkelas

import com.google.gson.annotations.SerializedName

data class BuatKelasPayload(

	@field:SerializedName("jadwal")
	val jadwal: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("ruang")
	val ruang: String? = null
)
