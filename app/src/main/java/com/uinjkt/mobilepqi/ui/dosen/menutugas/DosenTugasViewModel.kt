package com.uinjkt.mobilepqi.ui.dosen.menutugas

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.domain.model.tugas.DownloadNilaiModel
import com.mobilepqi.core.domain.model.tugas.GetListTugasModel
import com.mobilepqi.core.domain.usecase.tugas.MenuTugasUseCase
import kotlinx.coroutines.launch

class DosenTugasViewModel(private val useCase: MenuTugasUseCase) : ViewModel() {

    private val _getListTugas = MutableLiveData<Resource<GetListTugasModel>>()
    val getListTugas: LiveData<Resource<GetListTugasModel>> get() = _getListTugas

    private val _downloadNilai = MutableLiveData<Resource<DownloadNilaiModel>>()
    val downloadNilai: LiveData<Resource<DownloadNilaiModel>> get() = _downloadNilai

    fun getListTugas(idKelas: Int) {
        viewModelScope.launch {
            useCase.getListTugas(idKelas).collect {
                _getListTugas.value = it
            }
        }
    }

    fun downloadNilai(idKelas: Int) {
        viewModelScope.launch {
            useCase.downloadNilai(idKelas).collect {
                _downloadNilai.value = it
            }
        }
    }
}