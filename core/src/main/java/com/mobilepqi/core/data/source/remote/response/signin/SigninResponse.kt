package com.mobilepqi.core.data.source.remote.response.signin

import com.google.gson.annotations.SerializedName

data class SigninResponse(

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

        @field:SerializedName("nim")
        val nim: String? = null,

        @field:SerializedName("role")
        val role: String? = null,

        @field:SerializedName("updated_at")
        val updatedAt: String? = null,

        @field:SerializedName("name")
        val name: String? = null,

        @field:SerializedName("created_at")
        val createdAt: String? = null,

        @field:SerializedName("id")
        val id: Int? = null,

        @field:SerializedName("avatar")
        val avatar: String? = null,

        @field:SerializedName("kelas_id")
        val kelasId: Int? = null,

        @field:SerializedName("email")
        val email: String? = null,

        @field:SerializedName("token")
        val token: String? = null
    )
}
