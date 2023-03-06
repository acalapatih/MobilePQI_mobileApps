package com.uinjkt.mobilepqi.ui.dosen.menusilabus

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.data.source.remote.response.silabus.CreateSilabusPayload
import com.mobilepqi.core.domain.model.silabus.GetSilabusModel
import com.mobilepqi.core.domain.model.upload.UploadModel
import com.mobilepqi.core.domain.usecase.silabus.SilabusUsecase
import com.mobilepqi.core.domain.usecase.upload.UploadFileOrImageUsecase
import com.uinjkt.mobilepqi.util.Constant
import kotlinx.coroutines.launch
import java.io.File

class DosenSilabusViewModel(
    private val useCase: SilabusUsecase,
    private val uploadFileAndImageUsecase: UploadFileOrImageUsecase
) : ViewModel() {
    private val _createSilabus = MutableLiveData<Resource<Boolean>>()
    val createSilabus: LiveData<Resource<Boolean>> get() = _createSilabus

    private val _getSilabus = MutableLiveData<Resource<GetSilabusModel>>()
    val getSilabus: LiveData<Resource<GetSilabusModel>> get() = _getSilabus

    private val _deleteSilabus = MutableLiveData<Resource<Boolean>>()
    val deleteSilabus: LiveData<Resource<Boolean>> get() = _deleteSilabus

    private val _fileUploaded = MutableLiveData<Resource<UploadModel>>()
    val fileUploaded: LiveData<Resource<UploadModel>> get() = _fileUploaded

    fun createSilabus(request: CreateSilabusPayload, idKelas: Int) {
        viewModelScope.launch {
            useCase.createSilabus(request, idKelas).collect {
                _createSilabus.value = it
            }
        }
    }

    fun getSilabus(idKelas: Int) {
        viewModelScope.launch {
            useCase.getSilabus(idKelas).collect {
                _getSilabus.value = it
            }
        }
    }

    fun deleteSilabus(idKelas: Int) {
        viewModelScope.launch {
            useCase.deleteSilabus(idKelas).collect {
                _deleteSilabus.value = it
            }
        }
    }

    fun uploadFilePDF(key: String, type: String, file: File) {
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