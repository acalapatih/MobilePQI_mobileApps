package com.mobilepqi.core.domain.repository.menuqiroah

import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.data.source.remote.response.menuqiroah.CreateMateriQiroahPayload
import com.mobilepqi.core.domain.model.menuqiroah.GetMateriQiroahModel
import com.mobilepqi.core.domain.model.menuqiroah.MenuQiroahModel
import kotlinx.coroutines.flow.Flow

interface MenuQiroahRepository {
    fun createMateriQiroah(request: CreateMateriQiroahPayload): Flow<Resource<MenuQiroahModel>>
    fun getMateriQiroah(): Flow<Resource<GetMateriQiroahModel>>
}