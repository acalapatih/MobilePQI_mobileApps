package com.uinjkt.mobilepqi.ui.dosen.menutugas

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.domain.model.tugas.GetListTugasMahasiswaModel
import com.mobilepqi.core.domain.usecase.tugas.MenuTugasUseCase
import kotlinx.coroutines.launch

class DosenCekTugasMahasiswaViewModel(
    private val useCase: MenuTugasUseCase,
) : ViewModel() {

    private val _getListTugasMahasiswa = MutableLiveData<Resource<GetListTugasMahasiswaModel>>()
    val getListTugasMahasiswa: LiveData<Resource<GetListTugasMahasiswaModel>> get() = _getListTugasMahasiswa

    fun getListTugasMahasiswa(idTugas: Int, page: Int = 0, limit: Int = 0) {
        viewModelScope.launch {
            useCase.getListTugasMahasiswa(idTugas,page,limit).collect {
                _getListTugasMahasiswa.value = it
            }
        }
    }
}