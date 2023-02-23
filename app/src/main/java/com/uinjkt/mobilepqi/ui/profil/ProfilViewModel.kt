package com.uinjkt.mobilepqi.ui.profil

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.data.source.remote.response.profil.PutProfilPayload
import com.mobilepqi.core.domain.model.profil.ProfilModel
import com.mobilepqi.core.domain.model.profil.PutProfilModel
import com.mobilepqi.core.domain.usecase.profil.ProfilUsecase
import com.mobilepqi.core.domain.usecase.profil.PutProfilUsecase
import kotlinx.coroutines.launch

class ProfilViewModel(
    private val usecase: ProfilUsecase,
    private val usecasePut: PutProfilUsecase
): ViewModel() {
    private val _profil = MutableLiveData<Resource<ProfilModel>>()
    val profil: LiveData<Resource<ProfilModel>> get() = _profil

    private val _putprofil = MutableLiveData<Resource<PutProfilModel>> ()
    val putprofil: LiveData<Resource<PutProfilModel>> get() = _putprofil

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
}