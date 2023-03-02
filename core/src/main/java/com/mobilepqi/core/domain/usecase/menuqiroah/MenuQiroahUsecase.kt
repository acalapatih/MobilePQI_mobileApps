package com.mobilepqi.core.domain.usecase.menuqiroah

import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.data.source.remote.response.menuqiroah.CreateMateriQiroahPayload
import com.mobilepqi.core.data.source.remote.response.menuqiroah.UpdateDetailMateriQiroahPayload
import com.mobilepqi.core.domain.model.menuqiroah.*
import kotlinx.coroutines.flow.Flow

interface MenuQiroahUsecase {
    fun createMateriQiroah(request: CreateMateriQiroahPayload, idKelas: Int): Flow<Resource<CreateMateriQiroahModel>>
    fun getMateriQiroah(idKelas: Int): Flow<Resource<GetMateriQiroahModel>>
    fun getDetailMateriQiroah(id: Int): Flow<Resource<GetDetailMateriQiroahModel>>
    fun deleteMateriQiroah(idMateri: Int): Flow<Resource<DeleteMateriQiroahModel>>
    fun updateDetailMateriQiroah(request: UpdateDetailMateriQiroahPayload, idMateri: Int): Flow<Resource<UpdateDetailMateriQiroahModel>>
}