package com.uinjkt.mobilepqi.ui.mahasiswa.menuibadah

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.domain.model.menuibadah.GetMateriIbadahModel
import com.mobilepqi.core.domain.usecase.ibadah.MenuIbadahUsecase
import kotlinx.coroutines.launch

class MahasiswaMateriIbadahViewModel(private val useCase: MenuIbadahUsecase) : ViewModel() {
    private val _getMateri = MutableLiveData<Resource<GetMateriIbadahModel>>()
    val getMateri: LiveData<Resource<GetMateriIbadahModel>> get() = _getMateri

    fun getMateriIbadah(idKelas: Int){
        viewModelScope.launch {
            useCase.getMateriIbadah(idKelas).collect {
                _getMateri.value = it
            }
        }
    }
}