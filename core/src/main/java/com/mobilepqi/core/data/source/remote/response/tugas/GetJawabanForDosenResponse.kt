package com.mobilepqi.core.data.source.remote.response.tugas

import com.google.gson.annotations.SerializedName

data class GetJawabanForDosenResponse(

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

		@field:SerializedName("tugas")
		val tugas: Tugas? = null,

		@field:SerializedName("jawaban")
		val jawaban: Jawaban? = null,

		@field:SerializedName("user")
		val user: User? = null
	) {
		data class User(

			@field:SerializedName("nim")
			val nim: String? = null,

			@field:SerializedName("name")
			val name: String? = null,

			@field:SerializedName("avatar")
			val avatar: String? = null
		)

		data class Tugas(

			@field:SerializedName("description")
			val description: String? = null,

			@field:SerializedName("topic")
			val topic: String? = null,

			@field:SerializedName("id")
			val id: Int? = null,

			@field:SerializedName("title")
			val title: String? = null,

			@field:SerializedName("deadline")
			val deadline: String? = null
		)

		data class Jawaban(

			@field:SerializedName("tugas_id")
			val tugasId: Int? = null,

			@field:SerializedName("nim")
			val nim: String? = null,

			@field:SerializedName("file")
			val file: String? = null,

			@field:SerializedName("updated_at")
			val updatedAt: String? = null,

			@field:SerializedName("nilai")
			val nilai: Int? = null,

			@field:SerializedName("created_at")
			val createdAt: String? = null,

			@field:SerializedName("id")
			val id: Int? = null
		)
	}
}

