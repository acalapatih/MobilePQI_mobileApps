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
import com.mobilepqi.core.domain.model.common.FileItem
import com.uinjkt.mobilepqi.common.BaseActivity
import com.uinjkt.mobilepqi.data.DataSourceTugas
import com.uinjkt.mobilepqi.data.DataTugas
import com.uinjkt.mobilepqi.databinding.ActivityMahasiswaDetailTugasBinding
import com.uinjkt.mobilepqi.ui.dosen.menutugas.DosenDetailTugasViewModel
import com.uinjkt.mobilepqi.ui.mahasiswa.MahasiswaFileUploadedByAdapter
import com.uinjkt.mobilepqi.ui.mahasiswa.MahasiswaFileUploadedByAdapterList
import org.koin.androidx.viewmodel.ext.android.viewModel

class MahasiswaDetailTugasActivity : BaseActivity<ActivityMahasiswaDetailTugasBinding>(), MahasiswaFileUploadedByAdapter.OnUserClickListener {

    private lateinit var listTugasDosen: MutableList<DataTugas>
    private lateinit var listTugasMhs: MutableList<DataTugas>
    private lateinit var mahasiswaFileUploadedByAdapter: MahasiswaFileUploadedByAdapter
    private lateinit var dosenFileUploadedByAdapter: MahasiswaFileUploadedByAdapter

    companion object {
        @JvmStatic
        fun start(context: Context, idTugas: Int) {
            val starter = Intent(context, MahasiswaDetailTugasActivity::class.java)
                .putExtra(ID_TUGAS, idTugas)
            context.startActivity(starter)
        }
        private const val ID_TUGAS = "topic"
    }

    private val idTugas by lazy { intent.getIntExtra(ID_TUGAS, 0) }

    private val viewModel by viewModel<DosenDetailTugasViewModel>()

    private lateinit var listFileAttached: MutableList<FileItem>
    private lateinit var fileUploadedByDosenAdapter: MahasiswaFileUploadedByAdapterList
    private lateinit var topic: String
    private lateinit var title: String

    override fun getViewBinding(): ActivityMahasiswaDetailTugasBinding = ActivityMahasiswaDetailTugasBinding.inflate(layoutInflater)

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initListener()
        initObserver()

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

    private fun initView() {
        TODO("Not yet implemented")
    }

    private fun initListener() {
        TODO("Not yet implemented")
    }

    private fun initObserver() {
        TODO("Not yet implemented")
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