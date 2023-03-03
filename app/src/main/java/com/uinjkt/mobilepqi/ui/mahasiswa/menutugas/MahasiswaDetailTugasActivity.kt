package com.uinjkt.mobilepqi.ui.mahasiswa.menutugas

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.addCallback
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.uinjkt.mobilepqi.common.BaseActivity
import com.uinjkt.mobilepqi.data.DataSourceTugas
import com.uinjkt.mobilepqi.data.DataTugas
import com.uinjkt.mobilepqi.databinding.ActivityMahasiswaDetailTugasBinding
import com.uinjkt.mobilepqi.ui.mahasiswa.MahasiswaFileUploadedByAdapter

class MahasiswaDetailTugasActivity : BaseActivity<ActivityMahasiswaDetailTugasBinding>(), MahasiswaFileUploadedByAdapter.OnUserClickListener {

    private lateinit var listTugasDosen: MutableList<DataTugas>
    private lateinit var listTugasMhs: MutableList<DataTugas>
    private lateinit var mahasiswaFileUploadedByAdapter: MahasiswaFileUploadedByAdapter
    private lateinit var dosenFileUploadedByAdapter: MahasiswaFileUploadedByAdapter

    companion object {
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, MahasiswaDetailTugasActivity::class.java)
            context.startActivity(starter)
        }
    }

    override fun getViewBinding(): ActivityMahasiswaDetailTugasBinding = ActivityMahasiswaDetailTugasBinding.inflate(layoutInflater)

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        listTugasDosen = DataSourceTugas().dataTugas
        listTugasMhs = DataSourceTugas().getDataUploadTugas()


        // Initialize Adapter List Tugas Upload By Dosen
        mahasiswaFileUploadedByAdapter = MahasiswaFileUploadedByAdapter(this, listTugasDosen, "download",this)
        binding.rvFileUploadByDosen.adapter = mahasiswaFileUploadedByAdapter
        binding.rvFileUploadByDosen.layoutManager = LinearLayoutManager(this)

        // Initialize Adapter List Tugas Upload By Mhs
        dosenFileUploadedByAdapter = MahasiswaFileUploadedByAdapter(this, listTugasMhs, "delete",this)
        binding.rvFileUpload.adapter = dosenFileUploadedByAdapter
        binding.rvFileUpload.layoutManager = LinearLayoutManager(this)

        binding.rvFileUpload.visibility = View.GONE

        if(binding.rvFileUpload.isVisible) {
            binding.btnKirimFileUpload.text = "Tandai Selesai"
        }

        binding.ivLogoBackCircleButtonTugasMahasiswa.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding.btnTambahFileUpload.setOnClickListener {
            showToast("File Berhasil Ditambahkan")
            binding.rvFileUpload.visibility = View.VISIBLE
        }

        binding.btnKirimFileUpload.setOnClickListener {
            showOneActionDialogWithInvoke("File Berhasil Dikirim", "Okay") {
                onBackPressedDispatcher.onBackPressed()
            }
        }

        onBackPressedDispatcher.addCallback(this) {
            finish()
        }

    }

    override fun onUserClickListener(action: String) {
        if (action == "delete") {
            showToast("File Deleted", Toast.LENGTH_SHORT)
            binding.rvFileUpload.visibility = View.GONE
        } else {
            showToast("File Downloaded", Toast.LENGTH_SHORT)
        }
    }
}