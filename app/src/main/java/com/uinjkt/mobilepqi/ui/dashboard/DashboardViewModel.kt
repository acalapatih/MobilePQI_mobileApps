package com.uinjkt.mobilepqi.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.domain.model.JadwalSholatModel
import com.mobilepqi.core.domain.usecase.JadwalSholatUsecase
import kotlinx.coroutines.launch

class DashboardViewModel(private val jadwalSholatUsecase: JadwalSholatUsecase) : ViewModel() {
    private val _jadwalSholat = MutableLiveData<Resource<JadwalSholatModel>>()
    val jadwalSholat: LiveData<Resource<JadwalSholatModel>> get() = _jadwalSholat

    fun getJadwalSholat(latitude: String, longitude: String) {
        viewModelScope.launch {
            jadwalSholatUsecase.getJadwalSholat(latitude, longitude).collect {
                _jadwalSholat.value = it
            }
        }
    }
}