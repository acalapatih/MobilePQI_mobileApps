package com.uinjkt.mobilepqi.ui.kelas.daftarkelas

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.domain.model.daftarkelas.DaftarKelasModel
import com.mobilepqi.core.domain.usecase.daftarkelas.DaftarKelasUsecase
import kotlinx.coroutines.launch

class DaftarKelasViewModel(private val usecase: DaftarKelasUsecase): ViewModel() {
    private val _daftarkelas = MutableLiveData<Resource<DaftarKelasModel>>()
    val daftarkelas: LiveData<Resource<DaftarKelasModel>> get() = _daftarkelas

    fun daftarkelas() {
        viewModelScope.launch {
            usecase.daftarkelas().collect {
                _daftarkelas.value = it
            }
        }
    }
}