package com.mobilepqi.core.domain.model.profil

import com.mobilepqi.core.data.source.remote.response.profil.ProfilResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

data class ProfilModel(
    val id: Int,
    val name: String,
    val nim: String,
    val email: String,
    val avatar: String,
    val role: String,
    val faculty: String,
    val major: String,
    val birth: String,
    val phone: String,
    val address: String
) {
    companion object {
        fun mapResponseToModel(response: ProfilResponse): Flow<ProfilModel> {
            return flowOf(
                ProfilModel(
                    id = response.data?.id ?: 0,
                    name = response.data?.name ?: "",
                    nim = response.data?.nim ?: "",
                    email = response.data?.email ?: "",
                    avatar = response.data?.avatar ?: "",
                    role = response.data?.role ?: "",
                    faculty = response.data?.faculty ?: "",
                    major = response.data?.major ?: "",
                    birth = response.data?.birth ?: "",
                    phone = response.data?.phone ?: "",
                    address = response.data?.address ?: ""
                )
            )
        }
    }
}


