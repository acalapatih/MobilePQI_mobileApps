package com.mobilepqi.core.data.source.remote.response.tambahdosen

import com.google.gson.annotations.SerializedName

data class TambahDosenResponse(

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Int? = null
) {
	data class DosensItem(

		@field:SerializedName("errors")
		val errors: String? = null,

		@field:SerializedName("nim")
		val nim: String? = null,

		@field:SerializedName("name")
		val name: String? = null,

		@field:SerializedName("avatar")
		val avatar: String? = null
	)

	data class Data(

		@field:SerializedName("errors")
		val errors: String? = null,

		@field:SerializedName("dosen_count")
		val dosenCount: Int? = null,

		@field:SerializedName("dosens")
		val dosens: List<DosensItem?>? = null,

		@field:SerializedName("dosen_registered")
		val dosenRegistered: Int? = null
	)
}
