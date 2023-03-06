package com.uinjkt.mobilepqi.ui.mahasiswa.menusilabus

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.domain.model.silabus.GetSilabusModel
import com.mobilepqi.core.domain.usecase.silabus.SilabusUsecase
import kotlinx.coroutines.launch

class MahasiswaSilabusViewModel(
    private val useCase: SilabusUsecase
) : ViewModel() {
    private val _getSilabus = MutableLiveData<Resource<GetSilabusModel>>()
    val getSilabus: LiveData<Resource<GetSilabusModel>> get() = _getSilabus

    fun getSilabus(idKelas: Int) {
        viewModelScope.launch {
            useCase.getSilabus(idKelas).collect {
                _getSilabus.value = it
            }
        }
    }
}