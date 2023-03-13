package com.uinjkt.mobilepqi.ui.dosen.menutugas

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.domain.model.tugas.DownloadNilaiModel
import com.mobilepqi.core.domain.model.tugas.GetListTopicTugasModel
import com.mobilepqi.core.domain.usecase.tugas.MenuTugasUseCase
import kotlinx.coroutines.launch

class DosenTugasFilterViewModel(private val useCase: MenuTugasUseCase) : ViewModel() {

    private val _getListTopicTugas = MutableLiveData<Resource<GetListTopicTugasModel>>()
    val getListTopicTugas: LiveData<Resource<GetListTopicTugasModel>> get() = _getListTopicTugas

    private val _downloadNilai = MutableLiveData<Resource<DownloadNilaiModel>>()
    val downloadNilai: LiveData<Resource<DownloadNilaiModel>> get() = _downloadNilai

    fun getListTopicTugas(idKelas: Int, topic: String) {
        viewModelScope.launch {
            useCase.getLisTopicTugas(idKelas,topic).collect {
                _getListTopicTugas.value = it
            }
        }
    }

    fun downloadNilai(idKelas: Int, topic: String) {
        viewModelScope.launch {
            useCase.downloadNilai(idKelas,topic).collect {
                _downloadNilai.value = it
            }
        }
    }


}