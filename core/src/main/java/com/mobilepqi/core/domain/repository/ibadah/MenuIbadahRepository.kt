package com.mobilepqi.core.domain.repository.ibadah

import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.data.source.remote.response.ibadah.CreateMateriIbadahPayload
import com.mobilepqi.core.data.source.remote.response.ibadah.UpdateDetailMateriIbadahPayload
import com.mobilepqi.core.domain.model.menuibadah.*
import kotlinx.coroutines.flow.Flow

interface MenuIbadahRepository {
    fun createMateriIbadah(request: CreateMateriIbadahPayload, idKelas: Int): Flow<Resource<CreateMateriIbadahModel>>
    fun getMateriIbadah(idKelas : Int): Flow<Resource<GetMateriIbadahModel>>
    fun getDetailMateriIbadah(id: Int): Flow<Resource<GetDetailMateriIbadahModel>>
    fun deleteMateriIbadah(idMateri: Int): Flow<Resource<DeleteMateriIbadahModel>>
    fun updateDetailMateriIbadah(request: UpdateDetailMateriIbadahPayload, idMateri: Int): Flow<Resource<UpdateDetailMateriIbadahModel>>
}