package com.mobilepqi.core.domain.model.tugas

import com.mobilepqi.core.data.source.remote.response.tugas.GetJawabanForDosenResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

data class GetJawabanForDosenModel(
    val user: User,
    val tugas: Tugas,
    val jawaban: Jawaban,
) {
    data class User(
        val nim: String,
        val name: String,
        val avatar: String,
    )

    data class Tugas(
        val description: String,
        val topic: String,
        val id: Int,
        val title: String,
        val deadline: String,
    )

    data class Jawaban(
        val tugasId: Int,
        val nim: String,
        val file: String,
        val updatedAt: String,
        val nilai: Int,
        val createdAt: String,
        val id: Int,
    )

    companion object {
        fun mapResponseToModel(response: GetJawabanForDosenResponse): Flow<GetJawabanForDosenModel> {
            return flowOf(
                GetJawabanForDosenModel(
                    user = response.data?.user.let { user ->
                        User(
                            nim = user?.nim ?: "",
                            name = user?.name ?: "",
                            avatar = user?.avatar ?: ""
                        )
                    },
                    tugas = response.data?.tugas.let { tugas ->
                        Tugas(
                            description = tugas?.description ?: "",
                            topic = tugas?.topic ?: "",
                            id = tugas?.id ?: 0,
                            title = tugas?.title ?: "",
                            deadline = tugas?.deadline ?: "",
                        )
                    },
                    jawaban = response.data?.jawaban.let { jawaban ->
                        Jawaban(
                            tugasId = jawaban?.tugasId ?: 0,
                            nim = jawaban?.nim ?: "",
                            file = jawaban?.file ?: "",
                            updatedAt = jawaban?.updatedAt ?: "",
                            nilai = jawaban?.nilai ?: 0,
                            createdAt = jawaban?.createdAt ?: "",
                            id = jawaban?.id ?: 0,
                        )

                    }
                )
            )
        }
    }

}
