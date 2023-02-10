package com.uinjkt.mobilepqi.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.domain.model.jadwalsholat.JadwalSholatModel
import com.mobilepqi.core.domain.model.uploadimage.UploadImageModel
import com.mobilepqi.core.domain.usecase.jadwalsholat.JadwalSholatUsecase
import com.mobilepqi.core.domain.usecase.uploadimage.UploadImageUsecase
import java.io.File
import kotlinx.coroutines.launch

class DashboardViewModel(
    private val jadwalSholatUsecase: JadwalSholatUsecase,
    private val uploadImageUsecase: UploadImageUsecase
) : ViewModel() {
    private val _jadwalSholat = MutableLiveData<Resource<JadwalSholatModel>>()
    val jadwalSholat: LiveData<Resource<JadwalSholatModel>> get() = _jadwalSholat

    private val _imageUploaded = MutableLiveData<Resource<UploadImageModel>>()
    val imageUploaded: LiveData<Resource<UploadImageModel>> get() = _imageUploaded

    fun getJadwalSholat(latitude: String, longitude: String) {
        viewModelScope.launch {
            jadwalSholatUsecase.getJadwalSholat(latitude, longitude).collect {
                _jadwalSholat.value = it
            }
        }
    }

    fun uploadImage(image: File) {
        viewModelScope.launch {
            uploadImageUsecase.uploadImage(image).collect {
                _imageUploaded.value = it
            }
        }
    }
}