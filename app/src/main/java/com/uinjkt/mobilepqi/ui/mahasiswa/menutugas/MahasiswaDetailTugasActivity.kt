package com.uinjkt.mobilepqi.ui.mahasiswa.menutugas

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.addCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.data.source.remote.response.tugas.CreateJawabanPayload
import com.mobilepqi.core.domain.model.common.FileItem
import com.mobilepqi.core.domain.model.tugas.GetDetailTugasModel
import com.mobilepqi.core.domain.model.tugas.GetJawabanForMahasiswaModel
import com.uinjkt.mobilepqi.R
import com.uinjkt.mobilepqi.common.BaseActivity
import com.uinjkt.mobilepqi.databinding.ActivityMahasiswaDetailTugasBinding
import com.uinjkt.mobilepqi.ui.mahasiswa.MahasiswaFileUploadedByAdapterList
import com.uinjkt.mobilepqi.util.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File

class MahasiswaDetailTugasActivity : BaseActivity<ActivityMahasiswaDetailTugasBinding>(),
    MahasiswaFileUploadedByAdapterList.OnUserClickListener {

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
    private var urlFile = ""
    private var idJawabanMahasiswa = 0

    override fun getViewBinding(): ActivityMahasiswaDetailTugasBinding =
        ActivityMahasiswaDetailTugasBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initAdapter()
        initView()
        initListener()
        initObserver()
    }

    private fun initView() {
        binding.tvTitleTugasDetailMahasiswa.text =
            getString(R.string.tv_title_tugas_detail_mahasiswa, "")
        binding.tvTitleTugasDetailMahasiswa.text = getString(R.string.tv_tenggat_waktu_tugas, "")
        binding.tvNilaiTugas.text = getString(R.string.nilai_tugas, 0)

        getDetailTugas()
        getJawabanMahasiswa()
    }

    private fun getJawabanMahasiswa() {
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
            openFileManager(launcherIntentFile)
        }

        binding.btnKirimFileUpload.setOnClickListener {
            when(binding.btnKirimFileUpload.text) {
                "Batalkan Pengiriman" -> {
                    showTwoActionDialog("Yakin batalkan pengiriman?", btnPositiveMessage = "Yes", btnNegativeMessage = "Batal") {
                        setListContentAvaiable(listFileMahasiswaAttached)
                        fileUploadedByMahasiswaAdapter = MahasiswaFileUploadedByAdapterList(this, listFileMahasiswaAttached,"delete", this)
                        binding.rvFileUpload.adapter = fileUploadedByMahasiswaAdapter
                        binding.tvUserTidakMengunggahFile.isVisible = false
                        binding.tvSilahkanUploadFile.isVisible = true
                    }
                }
                "Kirim" -> showTwoActionDialog("Kirim tugas", btnPositiveMessage = "Yes", btnNegativeMessage = "Batal") { createJawaban() }
                "Tandai Selesai" -> showTwoActionDialog("Yakin Tandai Selesai?", "Anda belum mengunggah dokumen", true, "Yes", "Batal") { createJawaban() }
            }
        }

        onBackPressedDispatcher.addCallback(this) {
            finish()
        }
    }

    private fun createJawaban() {
        val jawaban = listFileMahasiswaAttached.ifEmpty {
            mutableListOf(FileItem(""))
        }[0].url
        viewModel.createJawaban(CreateJawabanPayload(jawaban), idTugas)
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
        viewModel.getJawabanForMahasiswa.observe(this) { model ->
            when (model) {
                is Resource.Loading -> {
                    showRvloading(true)
                }
                is Resource.Success -> {
                    showRvloading(false)
                    model.data?.let {
                        actionAfterGetJawabanForMahasiswa(it)
                    }
                }
                is Resource.Error -> {
                    showToast(model.message ?: "Something Went Wrong")
                    showRvloading(false)
                }
            }
        }
        viewModel.fileUploaded.observe(this) { model ->
            when (model) {
                is Resource.Loading -> {
                    showRvloading(true)
                }
                is Resource.Success -> {
                    model.data?.fileUrl?.let { file ->
                        urlFile = file
                        listFileMahasiswaAttached = mutableListOf(FileItem(urlFile))
                        fileUploadedByMahasiswaAdapter.setData(listFileMahasiswaAttached)
                        if (!isChangingConfigurations) {
                            externalCacheDir?.let { cache -> deleteTempFile(cache) }
                        }
                    }
                    showRvloading(false)
                    setListContentAvaiable(listFileMahasiswaAttached)
                }
                is Resource.Error -> {
                    showToast(model.message ?: "Something Went Wrong")
                    showRvloading(false)
                }
            }
        }
        viewModel.createJawaban.observe(this) { model ->
            when (model) {
                is Resource.Loading -> {
                    showloading(true)
                }
                is Resource.Success -> {
                    getJawabanMahasiswa()
                    showloading(false)
                }
                is Resource.Error -> {
                    showOneActionDialog("Sudah Melewati Deadline", "Okay")
                    showloading(false)
                }
            }
        }

        viewModel.deleteJawaban.observe(this) { model ->
            when (model) {
                is Resource.Loading -> {
                    showRvloading(true)
                }
                is Resource.Success -> {
                    listFileMahasiswaAttached.removeFirst()
                    fileUploadedByMahasiswaAdapter.setData(listFileMahasiswaAttached)
                    setListContentAvaiable(listFileMahasiswaAttached)
                    idJawabanMahasiswa = 0
                    showRvloading(false)
                }
                is Resource.Error -> {
                    showRvloading(false)
                    showOneActionDialog("Jawaban Anda Sudah Dinilai", "Okay")
                }
            }
        }

        onBackPressedDispatcher.addCallback(this) {
            finish()
        }
    }

    private fun showRvloading(value: Boolean) {
        binding.pbFileLoading.isVisible = value
        binding.rvFileUpload.isInvisible = value
        binding.btnTambahFileUpload.isVisible = !value
    }

    private fun actionAfterGetJawabanForMahasiswa(model: GetJawabanForMahasiswaModel) {
        val file = model.file
        idJawabanMahasiswa = model.id

        if (file.isNotEmpty()) {
            listFileMahasiswaAttached = mutableListOf(FileItem(model.file))
            fileUploadedByMahasiswaAdapter.setData(listFileMahasiswaAttached)
        }
        setListContentAvaiable(listFileMahasiswaAttached)
        binding.tvNilaiTugas.text = getString(R.string.nilai_tugas, model.nilai)

        if (idJawabanMahasiswa != 0) {
            binding.tvSilahkanUploadFile.isVisible = false
            binding.btnTambahFileUpload.isVisible = false
            fileUploadedByMahasiswaAdapter =
                MahasiswaFileUploadedByAdapterList(this, listFileMahasiswaAttached, "hide")
            binding.rvFileUpload.adapter = fileUploadedByMahasiswaAdapter
            binding.btnKirimFileUpload.text = getString(R.string.batalkan_pengiriman)
            binding.tvUserTidakMengunggahFile.isVisible = file.isEmpty()
        }

    }

    private fun actionAfterGetDetailTugas(model: GetDetailTugasModel) {
        title = model.title

        topic = when (model.topic) {
            "hafalan surat" -> "hafalan surah"
            else -> model.topic
        }

        binding.tvTitleTugasDetailMahasiswa.text =
            getString(
                R.string.tv_title_tugas_detail_mahasiswa,
                "${topic.capitalizeEachWord()} > ${title.capitalizeEachWord()}"
            )
        binding.tvNamaTaskTugas.text = title
        binding.tvTenggatWaktuTugasMahasiswa.text =
            getString(
                R.string.tv_tenggat_waktu_tugas,
                model.deadline.convertTime("EEEE, dd MMMM yyyy (HH:mm)")
            )
        if (model.description.isBlank() || model.description.isEmpty()) {
            binding.tvDescriptionTugasDetail.text =
                getString(R.string.description_empty_state, "Tugas")
        } else {
            binding.tvDescriptionTugasDetail.text = model.description
        }
        listFileDosenAttached = model.file.toMutableList()
        fileUploadedByDosenAdapter.setData(listFileDosenAttached)
    }

    private fun initAdapter() {
        listFileMahasiswaAttached = mutableListOf()
        listFileDosenAttached = mutableListOf()

        fileUploadedByDosenAdapter =
            MahasiswaFileUploadedByAdapterList(this, listFileDosenAttached, "download", this)
        binding.rvFileUploadByDosen.adapter = fileUploadedByDosenAdapter
        fileUploadedByDosenAdapter.setData(listFileDosenAttached)
        binding.rvFileUploadByDosen.layoutManager = LinearLayoutManager(this)

        fileUploadedByMahasiswaAdapter =
            MahasiswaFileUploadedByAdapterList(this, listFileMahasiswaAttached, "delete", this)
        binding.rvFileUpload.adapter = fileUploadedByMahasiswaAdapter
        fileUploadedByMahasiswaAdapter.setData(listFileMahasiswaAttached)
        binding.rvFileUpload.layoutManager = LinearLayoutManager(this)
    }

    private fun showloading(value: Boolean) {
        binding.pbLoadingScreen.isVisible = value
        binding.nsvContentDetailTugasMahasiswa.isVisible = !value
    }

    override fun onUserClickListener(action: String, position: Int) {
        if (action == "delete") {
            if (idJawabanMahasiswa != 0) { deleteJawaban() }
            else {
                listFileMahasiswaAttached.removeAt(position)
                fileUploadedByMahasiswaAdapter.setData(listFileMahasiswaAttached)
                setListContentAvaiable(listFileMahasiswaAttached)
            }
        } else {
            val url = listFileDosenAttached[position].url
            downloadFileToStorage(this, url, url.getFileNameFromUrl())
        }
    }

    private fun deleteJawaban() {
        viewModel.deleteJawaban(idTugas)
    }

    private fun setListContentAvaiable(list: MutableList<FileItem>) {
        binding.btnTambahFileUpload.isVisible = list.isEmpty()
        binding.rvFileUpload.isVisible = list.isNotEmpty()
        binding.btnKirimFileUpload.text = if(list.isEmpty()) {
            getString(R.string.tandai_selesai)
        } else {
            getString(R.string.kirim)
        }
    }

    private val launcherIntentFile = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val selectedFile: Uri = result.data?.data as Uri
            val myFile = uriToFile(selectedFile, this, "file")
            viewModel.uploadFileOrImage(
                Constant.UPLOAD_KEY.JAWABAN,
                Constant.UPLOAD_TYPE.FILE,
                myFile
            )
        }
    }

    private fun deleteTempFile(file: File): Boolean {
        if (file.isDirectory) {
            val files = file.listFiles()
            if (files != null) {
                for (f in files) {
                    if (f.isDirectory) {
                        deleteTempFile(f)
                    } else {
                        f.delete()
                    }
                }
            }
        }
        return file.delete()
    }
}

