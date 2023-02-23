package com.mobilepqi.core.data.source.remote.response.signup

import com.google.gson.annotations.SerializedName

class SignupPayload (
    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("nim")
    val nim: String? = null,

    @field:SerializedName("email")
    val email: String? = null,

    @field:SerializedName("password")
    val password: String? = null,

    @field:SerializedName("class_code")
    val classCode: String? = null,
)