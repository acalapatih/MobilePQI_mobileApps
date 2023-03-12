package com.uinjkt.mobilepqi.ui.dosen.menutugas

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import androidx.activity.addCallback
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.jakewharton.rxbinding2.widget.RxTextView
import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.domain.model.tugas.GetJawabanForDosenModel
import com.uinjkt.mobilepqi.R
import com.uinjkt.mobilepqi.common.BaseActivity
import com.uinjkt.mobilepqi.databinding.ActivityDosenBeriNilaiTugasMahasiswaBinding
import com.uinjkt.mobilepqi.ui.dosen.DosenFileUploadedByMahasiswaAdapter
import com.uinjkt.mobilepqi.util.downloadFileToStorage
import com.uinjkt.mobilepqi.util.getFileNameFromUrl
import org.koin.androidx.viewmodel.ext.android.viewModel


class DosenBeriNilaiTugasMahasiswaActivity :
    BaseActivity<ActivityDosenBeriNilaiTugasMahasiswaBinding>(), DosenFileUploadedByMahasiswaAdapter.OnUserClickListener {

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
    private lateinit var editInputNilai: EditText

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
        editInputNilai = binding.etNilaiTugasMahasiswa
        val nilaiStream = RxTextView.textChanges(binding.etNilaiTugasMahasiswa)
            .skipInitialValue()
            .map { nilai ->
                nilai.isNotEmpty()
            }
        nilaiStream.subscribe { isValid ->
            if (isValid) {
                if (editInputNilai.text.toString().toInt() !in 0..100) {
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
                    showToast(model.message ?: "Something Went Wrong")
                    showloading(false)
                }
            }
        }
    }

    private fun afterGetJawabanForDosen(model: GetJawabanForDosenModel) {
        val dataUser = model.user
        val dataTugas = model.tugas
        val dataJawaban = model.jawaban
        // Initialize Adapter
        dosenFileUploadedByMahasiswaAdapter =
            DosenFileUploadedByMahasiswaAdapter(this, listOf(dataJawaban), this)
        binding.rvFileUpload.adapter = dosenFileUploadedByMahasiswaAdapter

        // Set Layout Manager
        binding.rvFileUpload.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        // Show User
        binding.tvCekTugasNamaMahasiswa.text = dataUser.name
        binding.tvCekTugasNimMahasiswa.text = dataUser.nim
        Glide.with(baseContext)
            .load(dataUser.avatar)
            .placeholder(R.drawable.img_user)
            .into(binding.ivProfilePictureMahasiswa)

        // Show Tugas
        binding.tvTitleTugasDetailMahasiswa.text = getString(R.string.tv_title_tugas_detail_mahasiswa, dataTugas.title)
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


}