package com.uinjkt.mobilepqi.ui.dosen.menutugas

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.data.source.remote.response.tugas.CreateNilaiPayload
import com.mobilepqi.core.domain.model.tugas.GetJawabanForDosenModel
import com.mobilepqi.core.domain.usecase.tugas.MenuTugasUseCase
import kotlinx.coroutines.launch

class DosenBeriNilaiTugasMahasiswaViewModel(
    private val useCase: MenuTugasUseCase
): ViewModel() {

    private val _getJawabanForDosen = MutableLiveData<Resource<GetJawabanForDosenModel>>()
    val getJawabanForDosen: LiveData<Resource<GetJawabanForDosenModel>> get() = _getJawabanForDosen

    private val _createNilai = MutableLiveData<Resource<Boolean>>()
    val createNilai: LiveData<Resource<Boolean>> get() = _createNilai


    fun getJawabanForDosen(idTugas: Int, nim: String) {
        viewModelScope.launch {
            useCase.getJawabanForDosen(idTugas, nim).collect {
                _getJawabanForDosen.value = it
            }
        }
    }

    fun createNilai(request: CreateNilaiPayload, idJawaban: Int) {
        viewModelScope.launch {
            useCase.createNilai(request, idJawaban).collect {
                _createNilai.value = it
            }
        }
    }

}