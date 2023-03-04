package com.uinjkt.mobilepqi.ui.dosen.menuqiroah

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.data.source.remote.response.menuqiroah.UpdateDetailMateriQiroahPayload
import com.mobilepqi.core.domain.model.menuqiroah.DeleteMateriQiroahModel
import com.mobilepqi.core.domain.model.menuqiroah.GetDetailMateriQiroahModel
import com.mobilepqi.core.domain.model.menuqiroah.UpdateDetailMateriQiroahModel
import com.mobilepqi.core.domain.model.upload.UploadModel
import com.mobilepqi.core.domain.usecase.menuqiroah.MenuQiroahUsecase
import com.mobilepqi.core.domain.usecase.upload.UploadFileOrImageUsecase
import com.uinjkt.mobilepqi.util.Constant
import kotlinx.coroutines.launch
import java.io.File

class DosenMateriDetailQiroahViewModel(
    private val useCase: MenuQiroahUsecase,
    private val uploadFileAndImageUsecase: UploadFileOrImageUsecase
): ViewModel() {

    private val _getDetailMateri = MutableLiveData<Resource<GetDetailMateriQiroahModel>>()
    val getDetailMateri: LiveData<Resource<GetDetailMateriQiroahModel>> get() = _getDetailMateri

    private val _deleteMateri = MutableLiveData<Resource<DeleteMateriQiroahModel>>()
    val deleteMateri: LiveData<Resource<DeleteMateriQiroahModel>> get() = _deleteMateri

    private val _updateDetailMateri = MutableLiveData<Resource<UpdateDetailMateriQiroahModel>>()
    val updateDetailMateri: LiveData<Resource<UpdateDetailMateriQiroahModel>> get() = _updateDetailMateri

    private val _fileUploaded = MutableLiveData<Resource<UploadModel>>()
    val fileUploaded: LiveData<Resource<UploadModel>> get() = _fileUploaded

    fun getDetailMateriQiroah(idMateri: Int){
        viewModelScope.launch {
            useCase.getDetailMateriQiroah(idMateri).collect {
                _getDetailMateri.value = it
            }
        }
    }

    fun deletemateriQiroah(idMateri: Int) {
        viewModelScope.launch {
            useCase.deleteMateriQiroah(idMateri).collect {
                _deleteMateri.value = it
            }
        }
    }

    fun updateDetailMateriQiroah(request: UpdateDetailMateriQiroahPayload, idMateri: Int) {
        viewModelScope.launch {
            useCase.updateDetailMateriQiroah(request, idMateri).collect {
                _updateDetailMateri.value = it
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