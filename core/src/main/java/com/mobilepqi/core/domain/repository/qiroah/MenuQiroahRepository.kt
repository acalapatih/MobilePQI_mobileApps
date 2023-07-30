package com.mobilepqi.core.domain.repository.qiroah

import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.data.source.remote.response.qiroah.CreateMateriQiroahPayload
import com.mobilepqi.core.data.source.remote.response.qiroah.UpdateDetailMateriQiroahPayload
import com.mobilepqi.core.domain.model.menuqiroah.*
import kotlinx.coroutines.flow.Flow

interface MenuQiroahRepository {
    fun createMateriQiroah(request: CreateMateriQiroahPayload, idKelas: Int): Flow<Resource<CreateMateriQiroahModel>>
    fun getMateriQiroah(idKelas : Int): Flow<Resource<GetMateriQiroahModel>>
    fun getDetailMateriQiroah(id: Int): Flow<Resource<GetDetailMateriQiroahModel>>
    fun deleteMateriQiroah(idMateri: Int): Flow<Resource<DeleteMateriQiroahModel>>
    fun updateDetailMateriQiroah(request: UpdateDetailMateriQiroahPayload, idMateri: Int): Flow<Resource<UpdateDetailMateriQiroahModel>>
}