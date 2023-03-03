package com.uinjkt.mobilepqi.ui.kelas.tambahdosen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.domain.model.tambahdosen.TambahDosenModel
import com.mobilepqi.core.domain.usecase.tambahdosen.TambahDosenUsecase
import kotlinx.coroutines.launch

class TambahDosenViewModel(private val usecase: TambahDosenUsecase): ViewModel() {
    private val _tambahdosen = MutableLiveData<Resource<TambahDosenModel>>()
    val tambahdosen: LiveData<Resource<TambahDosenModel>> get() = _tambahdosen

    fun tambahdosen(idKelas: Int) {
        viewModelScope.launch {
            usecase.tambahdosen(idKelas).collect {
                _tambahdosen.value = it
            }
        }
    }
}