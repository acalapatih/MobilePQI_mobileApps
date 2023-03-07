package com.mobilepqi.core.domain.usecase.ibadah

import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.data.source.remote.response.ibadah.CreateMateriIbadahPayload
import com.mobilepqi.core.data.source.remote.response.ibadah.UpdateDetailMateriIbadahPayload
import com.mobilepqi.core.domain.model.menuibadah.*
import com.mobilepqi.core.domain.repository.ibadah.MenuIbadahRepository
import kotlinx.coroutines.flow.Flow

class MenuIbadahInteractor(
    private val repository: MenuIbadahRepository
) : MenuIbadahUsecase {

    override fun createMateriIbadah(request: CreateMateriIbadahPayload, idKelas: Int): Flow<Resource<CreateMateriIbadahModel>> =
        repository.createMateriIbadah(request, idKelas)

    override fun getMateriIbadah(idKelas: Int): Flow<Resource<GetMateriIbadahModel>> = repository.getMateriIbadah(idKelas)
    override fun getDetailMateriIbadah(id: Int): Flow<Resource<GetDetailMateriIbadahModel>> = repository.getDetailMateriIbadah(id)
    override fun deleteMateriIbadah(idMateri: Int): Flow<Resource<DeleteMateriIbadahModel>> = repository.deleteMateriIbadah(idMateri)
    override fun updateDetailMateriIbadah(
        request: UpdateDetailMateriIbadahPayload,
        idMateri: Int
    ): Flow<Resource<UpdateDetailMateriIbadahModel>> = repository.updateDetailMateriIbadah(request, idMateri)
}