package com.mobilepqi.core.domain.model.tugas

import com.mobilepqi.core.data.source.remote.response.tugas.GetListTugasMahasiswaResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf


data class GetListTugasMahasiswaModel(
    val count: Int,
    val jawaban: List<JawabanItem>? = null,
) {
    data class JawabanItem(
        val nim: String,
        val name: String,
        val avatar: String,
        val status: Boolean,
    )

    companion object {
        fun mapResponseToModel(response: GetListTugasMahasiswaResponse): Flow<GetListTugasMahasiswaModel> {
            return flowOf(
                GetListTugasMahasiswaModel(
                    count = response.data?.count ?: 0,
                    jawaban = response.data?.jawaban?.map { mahasiswa ->
                        JawabanItem(
                            nim = mahasiswa?.nim ?: "",
                            name = mahasiswa?.name ?: "",
                            avatar = mahasiswa?.avatar ?: "",
                            status = mahasiswa?.status ?: false,
                        )
                    } ?: emptyList()
                )
            )
        }
    }
}
