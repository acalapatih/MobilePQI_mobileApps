package com.uinjkt.mobilepqi.ui.mahasiswa.menuqiroah

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.domain.model.menuqiroah.GetMateriQiroahModel
import com.mobilepqi.core.domain.usecase.qiroah.MenuQiroahUsecase
import kotlinx.coroutines.launch

class MahasiswaMateriQiroahViewModel(private val useCase: MenuQiroahUsecase) : ViewModel() {
    private val _getMateri = MutableLiveData<Resource<GetMateriQiroahModel>>()
    val getMateri: LiveData<Resource<GetMateriQiroahModel>> get() = _getMateri

    fun getMateriQiroah(idKelas: Int){
        viewModelScope.launch {
            useCase.getMateriQiroah(idKelas).collect {
                _getMateri.value = it
            }
        }
    }
}