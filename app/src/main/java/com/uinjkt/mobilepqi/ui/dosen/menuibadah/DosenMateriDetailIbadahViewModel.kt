package com.uinjkt.mobilepqi.ui.dosen.menuibadah

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.data.source.remote.response.ibadah.UpdateDetailMateriIbadahPayload
import com.mobilepqi.core.domain.model.menuibadah.DeleteMateriIbadahModel
import com.mobilepqi.core.domain.model.menuibadah.GetDetailMateriIbadahModel
import com.mobilepqi.core.domain.model.menuibadah.UpdateDetailMateriIbadahModel
import com.mobilepqi.core.domain.model.upload.UploadModel
import com.mobilepqi.core.domain.usecase.ibadah.MenuIbadahUsecase
import com.mobilepqi.core.domain.usecase.upload.UploadFileOrImageUsecase
import com.uinjkt.mobilepqi.util.Constant
import kotlinx.coroutines.launch
import java.io.File

class DosenMateriDetailIbadahViewModel(
    private val useCase: MenuIbadahUsecase,
    private val uploadFileAndImageUsecase: UploadFileOrImageUsecase
): ViewModel() {

    private val _getDetailMateri = MutableLiveData<Resource<GetDetailMateriIbadahModel>>()
    val getDetailMateri: LiveData<Resource<GetDetailMateriIbadahModel>> get() = _getDetailMateri

    private val _deleteMateri = MutableLiveData<Resource<DeleteMateriIbadahModel>>()
    val deleteMateri: LiveData<Resource<DeleteMateriIbadahModel>> get() = _deleteMateri

    private val _updateDetailMateri = MutableLiveData<Resource<UpdateDetailMateriIbadahModel>>()
    val updateDetailMateri: LiveData<Resource<UpdateDetailMateriIbadahModel>> get() = _updateDetailMateri

    private val _fileUploaded = MutableLiveData<Resource<UploadModel>>()
    val fileUploaded: LiveData<Resource<UploadModel>> get() = _fileUploaded

    fun getDetailMateriIbadah(idMateri: Int){
        viewModelScope.launch {
            useCase.getDetailMateriIbadah(idMateri).collect {
                _getDetailMateri.value = it
            }
        }
    }

    fun deletemateriIbadah(idMateri: Int) {
        viewModelScope.launch {
            useCase.deleteMateriIbadah(idMateri).collect {
                _deleteMateri.value = it
            }
        }
    }

    fun updateDetailMateriIbadah(request: UpdateDetailMateriIbadahPayload, idMateri: Int) {
        viewModelScope.launch {
            useCase.updateDetailMateriIbadah(request, idMateri).collect {
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