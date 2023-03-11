package com.uinjkt.mobilepqi.ui.dashboard.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.domain.model.dashboard.GetClassModel
import com.mobilepqi.core.domain.model.dashboard.GetTugasModel
import com.mobilepqi.core.domain.model.dashboard.GetUserModel
import com.mobilepqi.core.domain.model.jadwalsholat.JadwalSholatModel
import com.mobilepqi.core.domain.model.upload.UploadModel
import com.mobilepqi.core.domain.usecase.dashboard.getClass.GetClassUsecase
import com.mobilepqi.core.domain.usecase.dashboard.getTugas.GetTugasUsecase
import com.mobilepqi.core.domain.usecase.dashboard.getUser.GetUserUsecase
import com.mobilepqi.core.domain.usecase.jadwalsholat.JadwalSholatUsecase
import com.mobilepqi.core.domain.usecase.onboarding.OnboardingUsecase
import com.mobilepqi.core.domain.usecase.upload.UploadFileOrImageUsecase
import com.uinjkt.mobilepqi.util.Constant
import kotlinx.coroutines.launch
import java.io.File

class DashboardViewModel(
    private val jadwalSholatUsecase: JadwalSholatUsecase,
    private val uploadFileAndImageUsecase: UploadFileOrImageUsecase,
    private val loginUseCase: OnboardingUsecase,
    private val getTugasUsecase: GetTugasUsecase,
    private val getClassUsecase: GetClassUsecase,
    private val getUserusecase: GetUserUsecase
) : ViewModel() {
    private val _jadwalSholat = MutableLiveData<Resource<JadwalSholatModel>>()
    val jadwalSholat: LiveData<Resource<JadwalSholatModel>> get() = _jadwalSholat

    private val _imageUploaded = MutableLiveData<Resource<UploadModel>>()
    val imageUploaded: LiveData<Resource<UploadModel>> get() = _imageUploaded

    private val _userRole = MutableLiveData<String>()
    val userRole: LiveData<String> get() = _userRole

    private val _classId = MutableLiveData<Int>()
    val classId: LiveData<Int> get() = _classId

    private val _getTugas = MutableLiveData<Resource<GetTugasModel>>()
    val getTugas: LiveData<Resource<GetTugasModel>> get() = _getTugas

    private val _getClass = MutableLiveData<Resource<GetClassModel>>()
    val getClass: LiveData<Resource<GetClassModel>> get() = _getClass

    private val _getUser = MutableLiveData<Resource<GetUserModel>>()
    val getUser: LiveData<Resource<GetUserModel>> get() = _getUser

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

    fun getClassId() {
        _classId.value = loginUseCase.getClassId()
    }

    fun getTugas(idKelas: Int) {
        viewModelScope.launch {
            getTugasUsecase.getTugas(idKelas).collect {
                _getTugas.value = it
            }
        }
    }

    fun getClass(idKelas: Int) {
        viewModelScope.launch {
            getClassUsecase.getClass(idKelas).collect {
                _getClass.value = it
            }
        }
    }

    fun getUser() {
        viewModelScope.launch {
            getUserusecase.getUser().collect {
                _getUser.value = it
            }
        }
    }
}