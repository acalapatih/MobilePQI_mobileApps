package com.mobilepqi.core.data.source.remote.response.ibadah

import com.google.gson.annotations.SerializedName

data class CreateMateriIbadahPayload(
    @field:SerializedName("title")
    val title: String? = null
)