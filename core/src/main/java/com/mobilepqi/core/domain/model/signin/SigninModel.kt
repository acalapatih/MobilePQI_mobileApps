package com.mobilepqi.core.domain.model.signin

import com.mobilepqi.core.data.source.remote.response.signin.SigninResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

data class SigninModel(
    val id: Int,
    val name: String,
    val email: String,
    val avatar: String,
    val role: String,
    val token: String
) {
    companion object {
        fun mapResponseToModel(response: SigninResponse): Flow<SigninModel> {
            return flowOf(
                SigninModel(
                    id = response.data?.id ?: 0,
                    name = response.data?.name ?: "",
                    email = response.data?.email ?: "",
                    avatar = response.data?.avatar ?: "",
                    role = response.data?.role ?: "",
                    token = response.data?.token ?: ""
                )
            )
        }
    }
}
