package com.mobilepqi.core.domain.model.tambahdosen

import com.mobilepqi.core.data.source.remote.response.tambahdosen.PostTambahDosenResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

data class PostTambahDosenModel(
    val status: Int,
    val message: String,
    val data: Data? = null
) {
    data class Data(
        var errors: String
    )
    companion object {
        fun mapResponseToModel(response: PostTambahDosenResponse): Flow<PostTambahDosenModel> {
            return flowOf(
                PostTambahDosenModel(
                    status = response.status ?:0,
                    message = response.message ?:"",
                    data = Data(errors = response.data?.errors ?:"")
                )
            )
        }
    }
}
