package com.mobilepqi.core.domain.usecase.menuqiroah

import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.data.source.remote.response.menuqiroah.CreateMateriQiroahPayload
import com.mobilepqi.core.domain.model.menuqiroah.GetMateriQiroahModel
import com.mobilepqi.core.domain.model.menuqiroah.MenuQiroahModel
import com.mobilepqi.core.domain.repository.menuqiroah.MenuQiroahRepository
import kotlinx.coroutines.flow.Flow

class MenuQiroahInteractor(
    private val repository: MenuQiroahRepository
) : MenuQiroahUsecase {

    override fun createMateriQiroah(request: CreateMateriQiroahPayload): Flow<Resource<MenuQiroahModel>> =
        repository.createMateriQiroah(request)

    override fun getMateriQiroah(): Flow<Resource<GetMateriQiroahModel>> = repository.getMateriQiroah()
}