package com.uinjkt.mobilepqi.ui.dashboard.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.domain.model.jadwalsholat.JadwalSholatModel
import com.mobilepqi.core.domain.model.upload.UploadModel
import com.mobilepqi.core.domain.usecase.jadwalsholat.JadwalSholatUsecase
import com.mobilepqi.core.domain.usecase.onboarding.OnboardingUsecase
import com.mobilepqi.core.domain.usecase.upload.UploadFileOrImageUsecase
import com.uinjkt.mobilepqi.util.Constant
import java.io.File
import kotlinx.coroutines.launch

class DashboardViewModel(
    private val jadwalSholatUsecase: JadwalSholatUsecase,
    private val uploadFileAndImageUsecase: UploadFileOrImageUsecase,
    private val loginUseCase: OnboardingUsecase
) : ViewModel() {
    private val _jadwalSholat = MutableLiveData<Resource<JadwalSholatModel>>()
    val jadwalSholat: LiveData<Resource<JadwalSholatModel>> get() = _jadwalSholat

    private val _imageUploaded = MutableLiveData<Resource<UploadModel>>()
    val imageUploaded: LiveData<Resource<UploadModel>> get() = _imageUploaded

    private val _userRole = MutableLiveData<String>()
    val userRole: LiveData<String> get() = _userRole

    fun getJadwalSholat(timestamp: String, latitude: String, longitude: String) {
        viewModelScope.launch {
            jadwalSholatUsecase.getJadwalSholat(timestamp, latitude, longitude).collect {
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

    fun getUserRole() {
        _userRole.value = loginUseCase.getUserRole()
    }
}