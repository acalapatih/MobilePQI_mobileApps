package com.uinjkt.mobilepqi.ui.kelas.tambahdosen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.data.source.remote.response.tambahdosen.PostTambahDosenPayload
import com.mobilepqi.core.domain.model.tambahdosen.GetTambahDosenModel
import com.mobilepqi.core.domain.model.tambahdosen.PostTambahDosenModel
import com.mobilepqi.core.domain.usecase.tambahdosen.TambahDosenUsecase
import kotlinx.coroutines.launch

class TambahDosenViewModel(private val usecase: TambahDosenUsecase): ViewModel() {
    private val _getTambahDosen = MutableLiveData<Resource<GetTambahDosenModel>>()
    val getTambahdosen: LiveData<Resource<GetTambahDosenModel>> get() = _getTambahDosen

    private val _postTambahDosen = MutableLiveData<Resource<PostTambahDosenModel>>()
    val postTambahdosen: LiveData<Resource<PostTambahDosenModel>> get() = _postTambahDosen

    fun getTambahDosen(idKelas: Int, namaNip: String) {
        viewModelScope.launch {
            usecase.getTambahDosen(idKelas, namaNip).collect {
                _getTambahDosen.value = it
            }
        }
    }

    fun postTambahDosen(request: PostTambahDosenPayload, idKelas: Int) {
        viewModelScope.launch {
            usecase.postTambahDosen(request, idKelas).collect {
                _postTambahDosen.value = it
            }
        }
    }
}