package com.uinjkt.mobilepqi.ui.mahasiswa.menutugas

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.addCallback
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.domain.model.common.FileItem
import com.mobilepqi.core.domain.model.tugas.GetDetailTugasModel
import com.mobilepqi.core.domain.model.tugas.GetJawabanForMahasiswaModel
import com.uinjkt.mobilepqi.R
import com.uinjkt.mobilepqi.common.BaseActivity
import com.uinjkt.mobilepqi.databinding.ActivityMahasiswaDetailTugasBinding
import com.uinjkt.mobilepqi.ui.mahasiswa.MahasiswaFileUploadedByAdapterList
import com.uinjkt.mobilepqi.util.capitalizeEachWord
import com.uinjkt.mobilepqi.util.convertTime
import com.uinjkt.mobilepqi.util.downloadFileToStorage
import com.uinjkt.mobilepqi.util.getFileNameFromUrl
import org.koin.androidx.viewmodel.ext.android.viewModel

class MahasiswaDetailTugasActivity : BaseActivity<ActivityMahasiswaDetailTugasBinding>(), MahasiswaFileUploadedByAdapterList.OnUserClickListener {

    companion object {
        @JvmStatic
        fun start(context: Context, idTugas: Int) {
            val starter = Intent(context, MahasiswaDetailTugasActivity::class.java)
                .putExtra(ID_TUGAS, idTugas)
            context.startActivity(starter)
        }
        private const val ID_TUGAS = "idTugas"
    }

    private val idTugas by lazy { intent.getIntExtra(ID_TUGAS, 0) }

    private val viewModel by viewModel<MahasiswaDetailTugasViewModel>()

    private lateinit var listFileDosenAttached: MutableList<FileItem>
    private lateinit var listFileMahasiswaAttached: MutableList<FileItem>
    private lateinit var fileUploadedByDosenAdapter: MahasiswaFileUploadedByAdapterList
    private lateinit var fileUploadedByMahasiswaAdapter: MahasiswaFileUploadedByAdapterList
    private lateinit var topic: String
    private lateinit var title: String

    override fun getViewBinding(): ActivityMahasiswaDetailTugasBinding = ActivityMahasiswaDetailTugasBinding.inflate(layoutInflater)

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initAdapter()
        initView()
        initListener()
        initObserver()
    }

    private fun initView() {
        binding.tvTitleTugasDetailMahasiswa.text = getString(R.string.tv_title_tugas_detail_mahasiswa, "")
        binding.tvTitleTugasDetailMahasiswa.text = getString(R.string.tv_tenggat_waktu_tugas, "")
        binding.tvNilaiTugas.text = getString(R.string.nilai_tugas, 0)

        getDetailTugas()
        getUploadFileTugas()
    }

    private fun getUploadFileTugas() {
        viewModel.getJawabanForMahasiswa(idTugas)
    }

    private fun getDetailTugas() {
        viewModel.getDetailTugas(idTugas)
    }

    private fun initListener() {
        binding.ivLogoBackCircleButtonTugasMahasiswa.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding.btnTambahFileUpload.setOnClickListener {
            showToast("File Berhasil Ditambahkan")
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

    private fun initObserver() {
        viewModel.getDetailTugas.observe(this) { model ->
            when (model) {
                is Resource.Loading -> {
                    showloading(true)
                }
                is Resource.Success -> {
                    model.data?.let {
                        actionAfterGetDetailTugas(it)
                    }
                    showloading(false)
                }
                is Resource.Error -> {
                    showToast(model.message ?: "Something Went Wrong")
                    showloading(false)
                }
            }
        }
        viewModel.getJawabanForMahasiswa.observe(this) {model ->
            when (model) {
                is Resource.Loading -> {
                    showRvloading(true)
                }
                is Resource.Success -> {
                    model.data?.let {
                        actionAfterGetJawabanForMahasiswa(it)
                    }
                    showRvloading(false)
                }
                is Resource.Error -> {
                    showToast(model.message ?: "Something Went Wrong")
                    showRvloading(false)
                }
            }

        }
    }

    private fun showRvloading(value: Boolean) {
        binding.pbFileLoading.isVisible = value
        binding.btnKirimFileUpload.isVisible = !value
    }

    private fun actionAfterGetJawabanForMahasiswa(model: GetJawabanForMahasiswaModel) {
        listFileMahasiswaAttached.add(FileItem(model.file))
        fileUploadedByMahasiswaAdapter.setData(listFileMahasiswaAttached)
        binding.tvSilahkanUploadFile.isVisible = listFileMahasiswaAttached.isEmpty()
        binding.btnTambahFileUpload.isVisible = listFileMahasiswaAttached.isEmpty()
        binding.rvFileUpload.isVisible = listFileMahasiswaAttached.isNotEmpty()
        binding.tvNilaiTugas.text = getString(R.string.nilai_tugas, model.nilai)
    }

    private fun actionAfterGetDetailTugas(model: GetDetailTugasModel) {
        title = model.title

        topic = when (model.topic) {
            "hafalan surat" -> "hafalan surah"
            else -> model.topic
        }

        binding.tvTitleTugasDetailMahasiswa.text =
            getString(R.string.tv_title_tugas_detail_mahasiswa,
                "${topic.capitalizeEachWord()} > ${title.capitalizeEachWord()}")
        binding.tvNamaTaskTugas.text = title
        binding.tvTenggatWaktuTugasMahasiswa.text =
            getString(R.string.tv_tenggat_waktu_tugas,
                model.deadline.convertTime("EEEE, dd MMMM yyyy (HH:mm)"))
        binding.tvDescriptionTugasDetail.text = model.description
        listFileDosenAttached.addAll(0, model.file)
        fileUploadedByDosenAdapter.setData(listFileDosenAttached)
    }

    private fun initAdapter() {
        listFileMahasiswaAttached = mutableListOf()
        listFileDosenAttached = mutableListOf()

        fileUploadedByDosenAdapter =
            MahasiswaFileUploadedByAdapterList(this, listFileDosenAttached, "download", this)
        fileUploadedByDosenAdapter.setData(listFileDosenAttached)
        binding.rvFileUploadByDosen.adapter = fileUploadedByDosenAdapter
        binding.rvFileUploadByDosen.layoutManager = LinearLayoutManager(this)

        fileUploadedByMahasiswaAdapter = MahasiswaFileUploadedByAdapterList(this, listFileMahasiswaAttached, "delete",this)
        fileUploadedByMahasiswaAdapter.setData(listFileMahasiswaAttached)
        binding.rvFileUpload.adapter = fileUploadedByMahasiswaAdapter
        binding.rvFileUpload.layoutManager = LinearLayoutManager(this)
    }

    private fun showloading(value: Boolean) {
        binding.pbLoadingScreen.isVisible = value
        binding.nsvContentDetailTugasMahasiswa.isVisible =!value
    }

    override fun onUserClickListener(action: String, position: Int) {
        val url = listFileDosenAttached[position].url
        if (action == "delete") {
            listFileMahasiswaAttached.removeAt(position)
            binding.tvSilahkanUploadFile.isVisible = listFileMahasiswaAttached.isNullOrEmpty()
            binding.btnTambahFileUpload.isVisible = listFileMahasiswaAttached.isNullOrEmpty()
            binding.rvFileUpload.isVisible = listFileMahasiswaAttached.isNotEmpty()
            fileUploadedByMahasiswaAdapter.setData(listFileMahasiswaAttached)
        } else {
            downloadFileToStorage(this, url, url.getFileNameFromUrl())
        }
    }
}