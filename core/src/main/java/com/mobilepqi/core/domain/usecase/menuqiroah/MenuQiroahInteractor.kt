package com.mobilepqi.core.domain.usecase.menuqiroah

import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.data.source.remote.response.menuqiroah.CreateMateriQiroahPayload
import com.mobilepqi.core.data.source.remote.response.menuqiroah.UpdateDetailMateriQiroahPayload
import com.mobilepqi.core.domain.model.menuqiroah.*
import com.mobilepqi.core.domain.repository.menuqiroah.MenuQiroahRepository
import kotlinx.coroutines.flow.Flow

class MenuQiroahInteractor(
    private val repository: MenuQiroahRepository
) : MenuQiroahUsecase {

    override fun createMateriQiroah(request: CreateMateriQiroahPayload, idKelas: Int): Flow<Resource<CreateMateriQiroahModel>> =
        repository.createMateriQiroah(request, idKelas)

    override fun getMateriQiroah(idKelas: Int): Flow<Resource<GetMateriQiroahModel>> = repository.getMateriQiroah(idKelas)
    override fun getDetailMateriQiroah(id: Int): Flow<Resource<GetDetailMateriQiroahModel>> = repository.getDetailMateriQiroah(id)
    override fun deleteMateriQiroah(idMateri: Int): Flow<Resource<DeleteMateriQiroahModel>> = repository.deleteMateriQiroah(idMateri)
    override fun updateDetailMateriQiroah(
        request: UpdateDetailMateriQiroahPayload,
        idMateri: Int
    ): Flow<Resource<UpdateDetailMateriQiroahModel>> = repository.updateDetailMateriQiroah(request, idMateri)
}