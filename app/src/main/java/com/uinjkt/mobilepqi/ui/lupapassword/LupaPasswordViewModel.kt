package com.uinjkt.mobilepqi.ui.lupapassword

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.data.source.remote.response.lupapassword.LupaPasswordPayload
import com.mobilepqi.core.domain.usecase.lupapassword.LupaPasswordUsecase
import kotlinx.coroutines.launch

class LupaPasswordViewModel(private val useCase: LupaPasswordUsecase) : ViewModel() {
    private val _lupaPassword = MutableLiveData<Resource<Boolean>>()
    val lupaPassword: LiveData<Resource<Boolean>> get() = _lupaPassword

    fun lupaPassword(request: LupaPasswordPayload) {
        viewModelScope.launch {
            useCase.lupaPassword(request).collect {
                _lupaPassword.value = it
            }
        }
    }
}