package com.uinjkt.mobilepqi.ui.mahasiswa.menutugas

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.domain.model.tugas.GetListTugasModel
import com.mobilepqi.core.domain.usecase.tugas.MenuTugasUseCase
import kotlinx.coroutines.launch

class MahasiswaTugasViewModel(private val useCase: MenuTugasUseCase) : ViewModel() {

    private val _getListTugas = MutableLiveData<Resource<GetListTugasModel>>()
    val getListTugas: LiveData<Resource<GetListTugasModel>> get() = _getListTugas

    fun getListTugas(idKelas: Int) {
        viewModelScope.launch {
            useCase.getListTugas(idKelas).collect {
                _getListTugas.value = it
            }
        }
    }
}