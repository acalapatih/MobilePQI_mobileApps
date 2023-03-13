package com.mobilepqi.core.domain.model.dashboard

import com.mobilepqi.core.data.source.remote.response.dashboard.GetUserResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

data class GetUserModel(
    val id: Int,
    val name: String,
    val nim: String,
    val avatar: String
) {
    companion object {
        fun mapResponseToModel(response: GetUserResponse): Flow<GetUserModel> {
            return flowOf(
                GetUserModel(
                    id = response.data?.id ?: 0,
                    name = response.data?.name ?: "",
                    nim = response.data?.nim ?: "",
                    avatar = response.data?.avatar ?: ""
                )
            )
        }
    }
}
