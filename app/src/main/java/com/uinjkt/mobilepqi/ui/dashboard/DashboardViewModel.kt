package com.uinjkt.mobilepqi.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.domain.model.jadwalsholat.JadwalSholatModel
import com.mobilepqi.core.domain.model.upload.UploadModel
import com.mobilepqi.core.domain.usecase.jadwalsholat.JadwalSholatUsecase
import com.mobilepqi.core.domain.usecase.upload.UploadFileOrImageUsecase
import com.uinjkt.mobilepqi.util.Constant
import kotlinx.coroutines.launch
import java.io.File

class DashboardViewModel(
    private val jadwalSholatUsecase: JadwalSholatUsecase,
    private val uploadFileAndImageUsecase: UploadFileOrImageUsecase
) : ViewModel() {
    private val _jadwalSholat = MutableLiveData<Resource<JadwalSholatModel>>()
    val jadwalSholat: LiveData<Resource<JadwalSholatModel>> get() = _jadwalSholat

    private val _imageUploaded = MutableLiveData<Resource<UploadModel>>()
    val imageUploaded: LiveData<Resource<UploadModel>> get() = _imageUploaded

    fun getJadwalSholat(latitude: String, longitude: String) {
        viewModelScope.launch {
            jadwalSholatUsecase.getJadwalSholat(latitude, longitude).collect {
                _jadwalSholat.value = it
            }
        }
    }

    fun uploadImage(key: String, type: String, file: File) {
        /**
         * @see Constant.UPLOAD_KEY for the key
         * @see Constant.UPLOAD_TYPE for the type
         */
        viewModelScope.launch {
            uploadFileAndImageUsecase.uploadFileOrImage(key, type, file).collect {
                _imageUploaded.value = it
            }
        }
    }
}