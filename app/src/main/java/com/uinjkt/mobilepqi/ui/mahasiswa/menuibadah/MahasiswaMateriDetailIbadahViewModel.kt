package com.uinjkt.mobilepqi.ui.mahasiswa.menuibadah

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.domain.model.menuibadah.GetDetailMateriIbadahModel
import com.mobilepqi.core.domain.usecase.ibadah.MenuIbadahUsecase
import kotlinx.coroutines.launch

class MahasiswaMateriDetailIbadahViewModel(private val useCase: MenuIbadahUsecase): ViewModel() {

    private val _getDetailMateri = MutableLiveData<Resource<GetDetailMateriIbadahModel>>()
    val getDetailMateri: LiveData<Resource<GetDetailMateriIbadahModel>> get() = _getDetailMateri

    fun getDetailMateriIbadah(idMateri: Int){
        viewModelScope.launch {
            useCase.getDetailMateriIbadah(idMateri).collect {
                _getDetailMateri.value = it
            }
        }
    }

}