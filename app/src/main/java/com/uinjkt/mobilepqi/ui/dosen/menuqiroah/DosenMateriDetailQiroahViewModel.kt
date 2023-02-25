package com.uinjkt.mobilepqi.ui.dosen.menuqiroah

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.domain.model.menuqiroah.GetDetailMateriQiroahModel
import com.mobilepqi.core.domain.usecase.menuqiroah.MenuQiroahUsecase
import kotlinx.coroutines.launch

class DosenMateriDetailQiroahViewModel(private val useCase: MenuQiroahUsecase): ViewModel() {

    private val _getDetailMateri = MutableLiveData<Resource<GetDetailMateriQiroahModel>>()
    val getDetailMateri: LiveData<Resource<GetDetailMateriQiroahModel>> get() = _getDetailMateri

    fun getDetailMateriQiroah(){
        viewModelScope.launch {
            useCase.getDetailMateriQiroah().collect {
                _getDetailMateri.value = it
            }
        }
    }

}