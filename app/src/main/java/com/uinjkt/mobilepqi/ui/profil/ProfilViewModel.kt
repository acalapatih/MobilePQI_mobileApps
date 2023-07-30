package com.uinjkt.mobilepqi.ui.profil

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.data.source.remote.response.profil.PutProfilPayload
import com.mobilepqi.core.domain.model.profil.ProfilModel
import com.mobilepqi.core.domain.model.profil.PutProfilModel
import com.mobilepqi.core.domain.model.upload.UploadModel
import com.mobilepqi.core.domain.usecase.logout.LogoutUseCase
import com.mobilepqi.core.domain.usecase.profil.ProfilUsecase
import com.mobilepqi.core.domain.usecase.profil.PutProfilUsecase
import com.mobilepqi.core.domain.usecase.upload.UploadFileOrImageUsecase
import com.uinjkt.mobilepqi.util.Constant
import kotlinx.coroutines.launch
import java.io.File

class ProfilViewModel(
    private val usecase: ProfilUsecase,
    private val usecasePut: PutProfilUsecase,
    private val uploadFileAndImageUsecase: UploadFileOrImageUsecase,
    private val logoutUseCase: LogoutUseCase
): ViewModel() {
    private val _profil = MutableLiveData<Resource<ProfilModel>>()
    val profil: LiveData<Resource<ProfilModel>> get() = _profil

    private val _putprofil = MutableLiveData<Resource<PutProfilModel>> ()
    val putprofil: LiveData<Resource<PutProfilModel>> get() = _putprofil

    private val _uploadImage = MutableLiveData<Resource<UploadModel>>()
    val uploadImage: LiveData<Resource<UploadModel>> get() = _uploadImage

    fun profil() {
        viewModelScope.launch {
            usecase.profil().collect {
                _profil.value = it
            }
        }
    }

    fun putprofil(request: PutProfilPayload) {
        viewModelScope.launch {
            usecasePut.putprofil(request).collect {
                _putprofil.value = it
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
                _uploadImage.value = it
            }
        }
    }

    fun clearAllSharedPreferences() {
        logoutUseCase.clearAllSharedPreferences()
    }
}