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
    private val _login = MutableLiveData<Resource<SigninModel>>()
    val login: LiveData<Resource<SigninModel>> get() = _login

    fun login(request: SigninPayload) {
        viewModelScope.launch {
            useCase.signin(request).collect {
                _login.value = it
            }
        }
    }

    fun setToken(token: String) {
        viewModelScope.launch {
            useCase.setToken(token)
        }
    }
}