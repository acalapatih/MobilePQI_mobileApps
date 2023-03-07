package com.mobilepqi.core.domain.model.signin

import com.mobilepqi.core.data.source.remote.response.signin.SigninResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

data class SigninModel(
    val userId: Int,
    val name: String,
    val email: String,
    val avatar: String,
    val role: String,
    val token: String,
    val classId: Int
) {
    companion object {
        fun mapResponseToModel(response: SigninResponse): Flow<SigninModel> {
            return flowOf(
                SigninModel(
                    userId = response.data?.id ?: 0,
                    name = response.data?.name ?: "",
                    email = response.data?.email ?: "",
                    avatar = response.data?.avatar ?: "",
                    role = response.data?.role ?: "",
                    token = response.data?.token ?: "",
                    classId = response.data?.kelasId ?: 0
                )
            )
        }
    }
}
