package com.mobilepqi.core.domain.model.menuqiroah

import com.mobilepqi.core.data.source.remote.response.menuqiroah.CreateMateriQiroahResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

data class MenuQiroahModel (
    val id: Int,
    val createdBy: String,
    val type: String,
    val title: String
) {
    companion object {
        fun mapResponseToModel(response: CreateMateriQiroahResponse): Flow<MenuQiroahModel> {
            return flowOf(
                MenuQiroahModel(
                    id = response.data?.id ?:0,
                    createdBy = response.data?.createdBy ?:"",
                    type = response.data?.type ?:"qiroah",
                    title = response.data?.title ?:"",
                )
            )
        }
    }
}