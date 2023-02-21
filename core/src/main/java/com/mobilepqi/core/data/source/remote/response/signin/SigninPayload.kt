package com.mobilepqi.core.data.source.remote.response.signin

import com.google.gson.annotations.SerializedName

data class SigninPayload(
    @field:SerializedName("nim")
    val nim: String? = null,

    @field:SerializedName("password")
    val password: String? = null,
)
