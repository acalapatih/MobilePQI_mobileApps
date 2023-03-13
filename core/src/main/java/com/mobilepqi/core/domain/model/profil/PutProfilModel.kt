package com.mobilepqi.core.domain.model.profil

import com.mobilepqi.core.data.source.remote.response.profil.PutProfilResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

data class PutProfilModel(
    val id: Int,
    val faculty: String,
    val major: String,
    val phone: String,
    val address: String,
    val birth: String,
    val avatar: String
) {
    companion object {
        fun mapResponseToModel(response: PutProfilResponse): Flow<PutProfilModel> {
            return flowOf(
                PutProfilModel(
                    id = response.data?.id ?: 0,
                    faculty = response.data?.faculty ?: "",
                    major = response.data?.major ?: "",
                    phone = response.data?.phone ?: "",
                    address = response.data?.address ?: "",
                    birth = response.data?.birth ?: "",
                    avatar = response.data?.avatar ?: ""
                )
            )
        }
    }
}
