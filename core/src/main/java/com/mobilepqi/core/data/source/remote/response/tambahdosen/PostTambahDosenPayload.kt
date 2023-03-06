package com.mobilepqi.core.data.source.remote.response.tambahdosen

import com.google.gson.annotations.SerializedName

data class PostTambahDosenPayload(

	@field:SerializedName("dosen")
	val dosen: List<DosenItem?>? = null
) {
	data class DosenItem(

		@field:SerializedName("nim")
		val nim: String? = null
	)
}
