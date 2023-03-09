package com.uinjkt.mobilepqi.ui.dosen.menutugas

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.data.source.remote.response.tugas.CreateTugasPayload
import com.mobilepqi.core.domain.model.upload.UploadModel
import com.mobilepqi.core.domain.usecase.tugas.MenuTugasUseCase
import com.mobilepqi.core.domain.usecase.upload.UploadFileOrImageUsecase
import com.uinjkt.mobilepqi.util.Constant
import kotlinx.coroutines.launch
import java.io.File

class DosenBuatTugasViewModel(
    private val useCase: MenuTugasUseCase,
    private val uploadFileAndImageUsecase: UploadFileOrImageUsecase,
) : ViewModel() {
    private val _createTugas = MutableLiveData<Resource<Boolean>>()
    val createTugas: LiveData<Resource<Boolean>> get() = _createTugas

    private val _fileUploaded = MutableLiveData<Resource<UploadModel>>()
    val fileUploaded: LiveData<Resource<UploadModel>> get() = _fileUploaded

    fun createTugas(request: CreateTugasPayload, idKelas: Int) {
        viewModelScope.launch {
            useCase.createTugas(request, idKelas).collect {
                _createTugas.value = it
            }
        }
    }

    fun uploadFileOrImage(key: String, type: String, file: File) {
        /**
         * @see Constant.UPLOAD_KEY for the key
         * @see Constant.UPLOAD_TYPE for the type
         */
        viewModelScope.launch {
            uploadFileAndImageUsecase.uploadFileOrImage(key, type, file).collect {
                _fileUploaded.value = it
            }
        }
    }
}