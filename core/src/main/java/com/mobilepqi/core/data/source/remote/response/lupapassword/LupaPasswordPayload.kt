package com.mobilepqi.core.data.source.remote.response.lupapassword

import com.google.gson.annotations.SerializedName

data class LupaPasswordPayload(
    @field:SerializedName("email")
    val email: String? = null,
)