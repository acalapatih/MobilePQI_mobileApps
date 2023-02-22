package com.uinjkt.mobilepqi.ui.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.data.source.remote.response.signin.SigninPayload
import com.mobilepqi.core.domain.model.signin.SigninModel
import com.mobilepqi.core.domain.usecase.signin.SigninUsecase
import kotlinx.coroutines.launch

class SigninViewModel(private val useCase: SigninUsecase) : ViewModel() {
    private val _signin = MutableLiveData<Resource<SigninModel>>()
    val signin: LiveData<Resource<SigninModel>> get() = _signin

    fun signin(request: SigninPayload) {
        viewModelScope.launch {
            useCase.signin(request).collect {
                _signin.value = it
            }
        }
    }

    fun setToken(token: String) {
        viewModelScope.launch {
            useCase.setToken(token)
        }
    }
}