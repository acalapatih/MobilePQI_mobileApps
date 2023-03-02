package com.uinjkt.mobilepqi.ui.dosen.menuqiroah

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.domain.model.menuqiroah.DeleteMateriQiroahModel
import com.mobilepqi.core.domain.model.menuqiroah.GetDetailMateriQiroahModel
import com.mobilepqi.core.domain.usecase.menuqiroah.MenuQiroahUsecase
import kotlinx.coroutines.launch

class DosenMateriDetailQiroahViewModel(private val useCase: MenuQiroahUsecase): ViewModel() {

    private val _getDetailMateri = MutableLiveData<Resource<GetDetailMateriQiroahModel>>()
    val getDetailMateri: LiveData<Resource<GetDetailMateriQiroahModel>> get() = _getDetailMateri

    private val _deleteMateri = MutableLiveData<Resource<DeleteMateriQiroahModel>>()
    val deleteMateri: LiveData<Resource<DeleteMateriQiroahModel>> get() = _deleteMateri

    fun getDetailMateriQiroah(idMateri: Int){
        viewModelScope.launch {
            useCase.getDetailMateriQiroah(idMateri).collect {
                _getDetailMateri.value = it
            }
        }
    }

    fun deletemateriQiroah(idMateri: Int) {
        viewModelScope.launch {
            useCase.deleteMateriQiroah(idMateri).collect {
                _deleteMateri.value = it
            }
        }
    }

}