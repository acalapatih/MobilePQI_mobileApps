package com.uinjkt.mobilepqi.ui.dosen.menuqiroah

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.data.source.remote.response.menuqiroah.CreateMateriQiroahPayload
import com.mobilepqi.core.domain.model.menuqiroah.CreateMateriQiroahModel
import com.mobilepqi.core.domain.model.menuqiroah.GetMateriQiroahModel
import com.mobilepqi.core.domain.usecase.menuqiroah.MenuQiroahUsecase
import kotlinx.coroutines.launch

class DosenMateriQiroahViewModel(private val useCase: MenuQiroahUsecase) : ViewModel() {

    private val _createMateri = MutableLiveData<Resource<CreateMateriQiroahModel>>()
    val createMateri: LiveData<Resource<CreateMateriQiroahModel>> get() = _createMateri

    private val _getMateri = MutableLiveData<Resource<GetMateriQiroahModel>>()
    val getMateri: LiveData<Resource<GetMateriQiroahModel>> get() = _getMateri


    fun createMateriQiroah(request: CreateMateriQiroahPayload, idKelas: Int){
        viewModelScope.launch {
            useCase.createMateriQiroah(request, idKelas).collect {
                _createMateri.value = it
            }
        }
    }
    fun getMateriQiroah(idKelas: Int){
        viewModelScope.launch {
            useCase.getMateriQiroah(idKelas).collect {
                _getMateri.value = it
            }
        }
    }
}