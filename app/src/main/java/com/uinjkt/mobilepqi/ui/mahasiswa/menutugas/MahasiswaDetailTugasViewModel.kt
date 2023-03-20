package com.uinjkt.mobilepqi.ui.mahasiswa.menutugas

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.data.source.remote.response.tugas.CreateJawabanPayload
import com.mobilepqi.core.domain.model.tugas.GetDetailTugasModel
import com.mobilepqi.core.domain.model.tugas.GetJawabanForMahasiswaModel
import com.mobilepqi.core.domain.model.upload.UploadModel
import com.mobilepqi.core.domain.usecase.tugas.MenuTugasUseCase
import com.mobilepqi.core.domain.usecase.upload.UploadFileOrImageUsecase
import com.uinjkt.mobilepqi.util.Constant
import kotlinx.coroutines.launch
import java.io.File

class MahasiswaDetailTugasViewModel(
    private val useCase: MenuTugasUseCase,
    private val uploadFileAndImageUsecase: UploadFileOrImageUsecase
) : ViewModel() {
    private val _getDetailTugas = MutableLiveData<Resource<GetDetailTugasModel>>()
    val getDetailTugas: LiveData<Resource<GetDetailTugasModel>> get() = _getDetailTugas

    private val _getJawabanForMahasiswa = MutableLiveData<Resource<GetJawabanForMahasiswaModel>>()
    val getJawabanForMahasiswa: LiveData<Resource<GetJawabanForMahasiswaModel>> get() = _getJawabanForMahasiswa

    private val _fileUploaded = MutableLiveData<Resource<UploadModel>>()
    val fileUploaded: LiveData<Resource<UploadModel>> get() = _fileUploaded

    private val _createJawaban = MutableLiveData<Resource<Boolean>>()
    val createJawaban: LiveData<Resource<Boolean>> get() = _createJawaban

    private val _deleteJawaban = MutableLiveData<Resource<Boolean>>()
    val deleteJawaban: LiveData<Resource<Boolean>> get() = _deleteJawaban

    fun getDetailTugas(idTugas: Int) {
        viewModelScope.launch {
            useCase.getDetailTugas(idTugas).collect {
                _getDetailTugas.value = it
            }
        }
    }

    fun getJawabanForMahasiswa(idTugas: Int) {
        viewModelScope.launch {
            useCase.getJawabanForMahasiswa(idTugas).collect {
                _getJawabanForMahasiswa.value = it
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

    fun createJawaban(request: CreateJawabanPayload, idTugas: Int) {
        viewModelScope.launch {
            useCase.createJawaban(request, idTugas).collect {
                _createJawaban.value = it
            }
        }
    }

    fun deleteJawaban(idTugas: Int) {
        viewModelScope.launch {
            useCase.deleteJawaban(idTugas).collect {
                _deleteJawaban.value = it
            }
        }
    }
}