package com.mobilepqi.core.data.source.remote.response.profil

import com.google.gson.annotations.SerializedName

data class ProfilResponse(

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

		@field:SerializedName("role")
		val role: String? = null,

		@field:SerializedName("address")
		val address: String? = null,

		@field:SerializedName("birth")
		val birth: String? = null,

		@field:SerializedName("created_at")
		val createdAt: String? = null,

		@field:SerializedName("avatar")
		val avatar: String? = null,

		@field:SerializedName("faculty")
		val faculty: String? = null,

		@field:SerializedName("nim")
		val nim: String? = null,

		@field:SerializedName("major")
		val major: String? = null,

		@field:SerializedName("updated_at")
		val updatedAt: String? = null,

		@field:SerializedName("phone")
		val phone: String? = null,

		@field:SerializedName("name")
		val name: String? = null,

		@field:SerializedName("id")
		val id: Int? = null,

		@field:SerializedName("email")
		val email: String? = null
	)
}
