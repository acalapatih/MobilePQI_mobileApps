package com.uinjkt.mobilepqi.ui.profil

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.domain.model.profil.ProfilModel
import com.mobilepqi.core.domain.usecase.profil.ProfilUsecase
import kotlinx.coroutines.launch

class ProfilViewModel(
    private val usecase: ProfilUsecase
): ViewModel() {
    private val _profil = MutableLiveData<Resource<ProfilModel>>()
    val profil: LiveData<Resource<ProfilModel>> get() = _profil

    fun profil() {
        viewModelScope.launch {
            usecase.profil().collect {
                _profil.value = it
            }
        }
    }
}