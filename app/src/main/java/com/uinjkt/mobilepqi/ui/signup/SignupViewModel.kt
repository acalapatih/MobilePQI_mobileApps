package com.uinjkt.mobilepqi.ui.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.data.source.remote.response.signup.SignupPayload
import com.mobilepqi.core.domain.usecase.signup.SignupUsecase
import kotlinx.coroutines.launch

class SignupViewModel(private val useCase: SignupUsecase) : ViewModel() {
    private val _signup = MutableLiveData<Resource<Boolean>>()
    val signup: LiveData<Resource<Boolean>> get() = _signup

    fun signup(request: SignupPayload) {
        viewModelScope.launch {
            useCase.signup(request).collect {
                _signup.value = it
            }
        }
    }
}