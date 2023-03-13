package com.mobilepqi.core.data.source.remote.response.tugas

import com.google.gson.annotations.SerializedName

data class GetListTugasResponse(

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Int? = null
) {
	data class Data(

		@field:SerializedName("errors")
		val errors: String? = null,

		@field:SerializedName("doa")
		val doa: List<DoaItem?>? = null,

		@field:SerializedName("surat")
		val surat: List<SuratItem?>? = null,

		@field:SerializedName("qiroah")
		val qiroah: List<QiroahItem?>? = null,

		@field:SerializedName("ibadah")
		val ibadah: List<IbadahItem?>? = null,

		@field:SerializedName("kelas_id")
		val kelasId: Int? = null
	) {
		data class DoaItem(

			@field:SerializedName("id")
			val id: Int? = null,

			@field:SerializedName("title")
			val title: String? = null,

			@field:SerializedName("deadline")
			val deadline: String? = null,

			@field:SerializedName("status")
			val status: Boolean? = null
		)

		data class QiroahItem(

			@field:SerializedName("id")
			val id: Int? = null,

			@field:SerializedName("title")
			val title: String? = null,

			@field:SerializedName("deadline")
			val deadline: String? = null,

			@field:SerializedName("status")
			val status: Boolean? = null
		)



		data class SuratItem(

			@field:SerializedName("id")
			val id: Int? = null,

			@field:SerializedName("title")
			val title: String? = null,

			@field:SerializedName("deadline")
			val deadline: String? = null,

			@field:SerializedName("status")
			val status: Boolean? = null
		)

		data class IbadahItem(

			@field:SerializedName("id")
			val id: Int? = null,

			@field:SerializedName("title")
			val title: String? = null,

			@field:SerializedName("deadline")
			val deadline: String? = null,

			@field:SerializedName("status")
			val status: Boolean? = null
		)
	}
}


