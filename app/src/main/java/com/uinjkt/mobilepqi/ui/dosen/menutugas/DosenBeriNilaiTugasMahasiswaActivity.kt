package com.uinjkt.mobilepqi.ui.dosen.menutugas

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.addCallback
import androidx.recyclerview.widget.LinearLayoutManager
import com.jakewharton.rxbinding2.widget.RxTextView
import com.uinjkt.mobilepqi.common.BaseActivity
import com.uinjkt.mobilepqi.data.DataSourceTugas
import com.uinjkt.mobilepqi.data.DataTugas
import com.uinjkt.mobilepqi.databinding.ActivityDosenBeriNilaiTugasMahasiswaBinding
import com.uinjkt.mobilepqi.ui.dosen.DosenFileUploadedByMahasiswaAdapter


class DosenBeriNilaiTugasMahasiswaActivity : BaseActivity<ActivityDosenBeriNilaiTugasMahasiswaBinding>() {

    private lateinit var listTugas: MutableList<DataTugas>
    private lateinit var dosenFileUploadedByMahasiswaAdapter: DosenFileUploadedByMahasiswaAdapter

    companion object {
        @JvmStatic
        fun start(context: Context, position: Int) {
            val starter = Intent(context, DosenBeriNilaiTugasMahasiswaActivity::class.java)
                .putExtra(ID,position)
            context.startActivity(starter)
        }
        private const val ID = "id"
    }

    override fun getViewBinding(): ActivityDosenBeriNilaiTugasMahasiswaBinding = ActivityDosenBeriNilaiTugasMahasiswaBinding.inflate(layoutInflater)

    @SuppressLint("CheckResult", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize data.
        listTugas = DataSourceTugas().dataTugas
        val getPositionMahasiswaId = intent.getIntExtra(ID, 0)
        val dataMahasiswa = DataSourceTugas().dataMahasiswa[getPositionMahasiswaId]

        // Initialize Adapter
        dosenFileUploadedByMahasiswaAdapter = DosenFileUploadedByMahasiswaAdapter(this, listTugas)
        binding.rvFileUpload.adapter = dosenFileUploadedByMahasiswaAdapter

        // Set Layout Manager
        binding.rvFileUpload.layoutManager= LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        binding.tvCekTugasNamaMahasiswa.text = dataMahasiswa.namaMahasiswa
        binding.tvCekTugasNimMahasiswa.text = dataMahasiswa.nimMahasiswa

        val editInputNilai = binding.etNilaiTugasMahasiswa

        val nilaiStream = RxTextView.textChanges(binding.etNilaiTugasMahasiswa)
            .skipInitialValue()
            .map { nilai ->
                nilai.isNotEmpty()
            }
        nilaiStream.subscribe{ isValid ->
            if (isValid) {
                if(editInputNilai.text.toString().toInt() !in 0..100) {
                    editInputNilai.setText("100")
                }
            }
        }

        binding.btnBeriNilai.setOnClickListener {
            if (editInputNilai.text.isNotEmpty()) {
                showOneActionDialogWithInvoke("Nilai Berhasil Ditambahkan", "Okay") {
                    onBackPressedDispatcher.onBackPressed()
                }
            } else {
                showOneActionDialog("Nilai Gagal Ditambahkan", "Okay")
            }
        }
        binding.ivLogoBackCircleButtonTugasDosen.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        onBackPressedDispatcher.addCallback(this) {
            finish()
        }
    }


}