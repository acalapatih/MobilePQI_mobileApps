package com.uinjkt.mobilepqi.ui.dosen.menutugas

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.addCallback
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.jakewharton.rxbinding2.widget.RxTextView
import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.data.source.remote.response.tugas.CreateNilaiPayload
import com.mobilepqi.core.domain.model.tugas.GetJawabanForDosenModel
import com.uinjkt.mobilepqi.R
import com.uinjkt.mobilepqi.common.BaseActivity
import com.uinjkt.mobilepqi.databinding.ActivityDosenBeriNilaiTugasMahasiswaBinding
import com.uinjkt.mobilepqi.ui.dosen.DosenFileUploadedByMahasiswaAdapter
import com.uinjkt.mobilepqi.util.capitalizeEachWord
import com.uinjkt.mobilepqi.util.downloadFileToStorage
import com.uinjkt.mobilepqi.util.getFileNameFromUrl
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.properties.Delegates


class DosenBeriNilaiTugasMahasiswaActivity :
    BaseActivity<ActivityDosenBeriNilaiTugasMahasiswaBinding>(),
    DosenFileUploadedByMahasiswaAdapter.OnUserClickListener {

    companion object {
        @JvmStatic
        fun start(context: Context, idTugas: Int, nim: String) {
            val starter = Intent(context, DosenBeriNilaiTugasMahasiswaActivity::class.java)
                .putExtra(ID_TUGAS, idTugas)
                .putExtra(NIM, nim)
            context.startActivity(starter)
        }

        private const val ID_TUGAS = "idTugas"
        private const val NIM = "nim"
    }

    private lateinit var dosenFileUploadedByMahasiswaAdapter: DosenFileUploadedByMahasiswaAdapter
    private var idJawaban by Delegates.notNull<Int>()

    private val viewModel by viewModel<DosenBeriNilaiTugasMahasiswaViewModel>()
    private val idTugas by lazy { intent.getIntExtra(ID_TUGAS, 0) }
    private val nim by lazy { intent.getStringExtra(NIM) ?: "" }

    override fun getViewBinding(): ActivityDosenBeriNilaiTugasMahasiswaBinding =
        ActivityDosenBeriNilaiTugasMahasiswaBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initListener()
        initObserver()
    }

    private fun initView() {
        binding.tvTitleTugasDetailMahasiswa.text =
            getString(R.string.tv_title_tugas_detail_mahasiswa, "")
        getJawabanForDosen()
    }

    private fun getJawabanForDosen() {
        viewModel.getJawabanForDosen(idTugas, nim)
    }

    @SuppressLint("CheckResult", "SetTextI18n")
    private fun initListener() {
        val editInputNilai = binding.etNilaiTugasMahasiswa
        val nilaiStream = RxTextView.textChanges(editInputNilai)
            .skipInitialValue()
            .map { nilai ->
                nilai.isNotEmpty()
            }
        nilaiStream.subscribe { isValid ->
            if (isValid) {
                if (editInputNilai.text.toString().toInt() !in 0..100) {
                    editInputNilai.setText("100")
                    editInputNilai.setSelection(editInputNilai.text.toString().length)
                }
                if (editInputNilai.text.toString().trim().length == 1 &&
                    editInputNilai.text.toString().trim() == "0"
                ) {
                    editInputNilai.setText("")
                }
            }
        }

        // Listener
        binding.btnBeriNilai.setOnClickListener {
            if (editInputNilai.text.isNotEmpty()) {
                val nilai = editInputNilai.text.toString().toInt()
                createNilai(nilai, idJawaban)
            } else {
                showOneActionDialog("Masukkan Nilai", "Okay")
            }
        }

        binding.ivLogoBackCircleButtonTugasDosen.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        onBackPressedDispatcher.addCallback(this) {
            finish()
        }
    }

    private fun createNilai(nilai: Int, idJawaban: Int) {
        viewModel.createNilai(CreateNilaiPayload(nilai), idJawaban)
    }


    private fun initObserver() {
        viewModel.getJawabanForDosen.observe(this) { model ->
            when (model) {
                is Resource.Loading -> {
                    showloading(true)
                }
                is Resource.Success -> {
                    model.data?.let {
                        afterGetJawabanForDosen(it)
                    }
                    showloading(false)
                }
                is Resource.Error -> {
                    if (model.message?.contains("not found") == true) {
                        showOneActionDialogWithInvoke(
                            "Mahasiswa belum mengumpulkan tugas",
                            "Okay"
                        ) {
                            onBackPressedDispatcher.onBackPressed()
                        }
                    } else {
                        showToast(model.message ?: "Something Went Wrong")
                    }
                    showloading(false)
                }
            }
        }
        viewModel.createNilai.observe(this) { model ->
            when (model) {
                is Resource.Loading -> {
                    showloading(true)
                }
                is Resource.Success -> {
                    showOneActionDialogWithInvoke("Nilai Berhasil Ditambahkan", "Okay") {
                        onBackPressedDispatcher.onBackPressed()
                    }
                    showloading(false)
                }
                is Resource.Error -> {
                    if (model.message?.contains("not found") == true) {
                        showOneActionDialogWithInvoke(
                            "Mahasiswa belum mengumpulkan tugas",
                            "Okay"
                        ) {
                            onBackPressedDispatcher.onBackPressed()
                        }
                    } else {
                        showToast(model.message ?: "Something Went Wrong")
                    }
                    showloading(false)
                }
            }
        }
        onBackPressedDispatcher.addCallback(this) {
            finish()
        }
    }

    private fun afterGetJawabanForDosen(model: GetJawabanForDosenModel) {

        val dataUser = model.user
        val dataTugas = model.tugas
        val dataJawaban = model.jawaban

        // Show Jawaban
        dosenFileUploadedByMahasiswaAdapter =
            if (dataJawaban.file.isEmpty() || dataJawaban.file.isBlank()) {
                DosenFileUploadedByMahasiswaAdapter(this, emptyList(), this)
            } else {
                DosenFileUploadedByMahasiswaAdapter(this, listOf(dataJawaban), this)
            }
        binding.rvFileUpload.adapter = dosenFileUploadedByMahasiswaAdapter
        binding.rvFileUpload.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        idJawaban = dataJawaban.id

        // Show User
        binding.tvCekTugasNamaMahasiswa.text = dataUser.name
        binding.tvCekTugasNimMahasiswa.text = getString(R.string.format_nim_mahasiswa, dataUser.nim)
        Glide.with(baseContext)
            .load(dataUser.avatar)
            .placeholder(R.drawable.img_user)
            .into(binding.ivProfilePictureMahasiswa)

        // Show Tugas
        binding.tvTitleTugasDetailMahasiswa.text = getString(
            R.string.tv_title_tugas_detail_mahasiswa,
            "${dataTugas.title.capitalizeEachWord()} > ${dataTugas.topic.capitalizeEachWord()}"
        )
        binding.etNilaiTugasMahasiswa.setText(dataJawaban.nilai.toString())

    }

    private fun showloading(value: Boolean) {
        binding.pbLoadingScreen.isVisible = value
        binding.nsvContentDetailListMahasiswa.isVisible = !value
    }

    override fun onUserClickListener(position: Int) {
        TODO("Not yet implemented")
    }

    override fun onUserDownloadClickListener(url: String) {
        downloadFileToStorage(this, url, url.getFileNameFromUrl())
    }

    override fun onRestart() {
        super.onRestart()
        getJawabanForDosen()
    }
}