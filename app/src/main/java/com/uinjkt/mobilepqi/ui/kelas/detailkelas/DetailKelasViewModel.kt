package com.uinjkt.mobilepqi.ui.kelas.detailkelas

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.domain.model.detailkelas.DetailKelasModel
import com.mobilepqi.core.domain.usecase.detailkelas.DetailKelasUsecase
import kotlinx.coroutines.launch

class DetailKelasViewModel(private val usecase: DetailKelasUsecase): ViewModel() {
    private val _detailkelas = MutableLiveData<Resource<DetailKelasModel>>()
    val detailkelas: LiveData<Resource<DetailKelasModel>> get() = _detailkelas

    fun detailkelas(idKelas: Int) {
        viewModelScope.launch {
            usecase.detailkelas(idKelas).collect {
                _detailkelas.value = it
            }
        }
    }
}