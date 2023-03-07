package com.uinjkt.mobilepqi.ui.dosen.menuibadah

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.data.source.remote.response.ibadah.CreateMateriIbadahPayload
import com.mobilepqi.core.domain.model.menuibadah.CreateMateriIbadahModel
import com.mobilepqi.core.domain.model.menuibadah.GetMateriIbadahModel
import com.mobilepqi.core.domain.usecase.ibadah.MenuIbadahUsecase
import kotlinx.coroutines.launch

class DosenMateriIbadahViewModel(private val useCase: MenuIbadahUsecase) : ViewModel() {

    private val _createMateri = MutableLiveData<Resource<CreateMateriIbadahModel>>()
    val createMateri: LiveData<Resource<CreateMateriIbadahModel>> get() = _createMateri

    private val _getMateri = MutableLiveData<Resource<GetMateriIbadahModel>>()
    val getMateri: LiveData<Resource<GetMateriIbadahModel>> get() = _getMateri


    fun createMateriIbadah(request: CreateMateriIbadahPayload, idKelas: Int){
        viewModelScope.launch {
            useCase.createMateriIbadah(request, idKelas).collect {
                _createMateri.value = it
            }
        }
    }
    fun getMateriIbadah(idKelas: Int){
        viewModelScope.launch {
            useCase.getMateriIbadah(idKelas).collect {
                _getMateri.value = it
            }
        }
    }
}