package com.mobilepqi.core.data.source.remote.response.silabus

import com.google.gson.annotations.SerializedName

data class CreateSilabusPayload (
    @field:SerializedName("silabus")
    val silabus: String? = null
)