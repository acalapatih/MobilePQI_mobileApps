package com.mobilepqi.core.data.source.remote.response.profil

import com.google.gson.annotations.SerializedName

data class PutProfilPayload(
    @field:SerializedName("faculty")
    val faculty: String? = null,

    @field:SerializedName("major")
    val major: String? = null,

    @field:SerializedName("phone")
    val phone: String? = null,

    @field:SerializedName("address")
    val address: String? = null,

    @field:SerializedName("birth")
    val birth: String? = null,

    @field:SerializedName("avatar")
    val avatar: String? = null,
)
