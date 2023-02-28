package com.uinjkt.mobilepqi.ui.kelas.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.data.source.remote.response.buatkelas.BuatKelasPayload
import com.mobilepqi.core.domain.model.buatkelas.BuatKelasModel
import com.mobilepqi.core.domain.usecase.buatkelas.BuatKelasUsecase
import kotlinx.coroutines.launch

class BuatKelasViewModel(
    private val usecase: BuatKelasUsecase
): ViewModel() {
    private val _buatkelas = MutableLiveData<Resource<BuatKelasModel>>()
    val buatkelas: LiveData<Resource<BuatKelasModel>> get() = _buatkelas

    fun buatkelas(request: BuatKelasPayload) {
        viewModelScope.launch {
            usecase.buatkelas(request).collect {
                _buatkelas.value = it
            }
        }
    }
}