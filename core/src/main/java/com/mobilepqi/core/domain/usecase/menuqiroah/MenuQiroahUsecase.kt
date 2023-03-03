package com.mobilepqi.core.domain.usecase.menuqiroah

import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.data.source.remote.response.menuqiroah.CreateMateriQiroahPayload
import com.mobilepqi.core.domain.model.menuqiroah.CreateMateriQiroahModel
import com.mobilepqi.core.domain.model.menuqiroah.GetDetailMateriQiroahModel
import com.mobilepqi.core.domain.model.menuqiroah.GetMateriQiroahModel
import kotlinx.coroutines.flow.Flow

interface MenuQiroahUsecase {
    fun createMateriQiroah(request: CreateMateriQiroahPayload, idKelas: Int): Flow<Resource<CreateMateriQiroahModel>>
    fun getMateriQiroah(idKelas: Int) : Flow<Resource<GetMateriQiroahModel>>
    fun getDetailMateriQiroah(id: Int): Flow<Resource<GetDetailMateriQiroahModel>>
}