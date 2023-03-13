package com.uinjkt.mobilepqi.ui.dosen.menutugas

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.domain.model.tugas.GetDetailTugasModel
import com.mobilepqi.core.domain.usecase.tugas.MenuTugasUseCase
import kotlinx.coroutines.launch

class DosenDetailTugasViewModel(
    private val useCase: MenuTugasUseCase,
) : ViewModel() {
    private val _getDetailTugas = MutableLiveData<Resource<GetDetailTugasModel>>()
    val getDetailTugas: LiveData<Resource<GetDetailTugasModel>> get() = _getDetailTugas

    fun getDetailTugas(idTugas: Int) {
        viewModelScope.launch {
            useCase.getDetailTugas(idTugas).collect {
                _getDetailTugas.value = it
            }
        }
    }
}