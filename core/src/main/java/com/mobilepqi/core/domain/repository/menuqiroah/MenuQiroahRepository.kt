package com.mobilepqi.core.domain.repository.menuqiroah

import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.data.source.remote.response.menuqiroah.CreateMateriQiroahPayload
import com.mobilepqi.core.domain.model.menuqiroah.CreateMateriQiroahModel
import com.mobilepqi.core.domain.model.menuqiroah.GetDetailMateriQiroahModel
import com.mobilepqi.core.domain.model.menuqiroah.GetMateriQiroahModel
import kotlinx.coroutines.flow.Flow

interface MenuQiroahRepository {
    fun createMateriQiroah(request: CreateMateriQiroahPayload): Flow<Resource<CreateMateriQiroahModel>>
    fun getMateriQiroah(): Flow<Resource<GetMateriQiroahModel>>
    fun getDetailMateriQiroah(id: Int): Flow<Resource<GetDetailMateriQiroahModel>>
}