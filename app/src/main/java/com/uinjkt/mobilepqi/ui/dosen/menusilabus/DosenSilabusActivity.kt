package com.uinjkt.mobilepqi.ui.dosen.menusilabus

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Gravity
import androidx.activity.addCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.data.source.remote.response.silabus.CreateSilabusPayload
import com.uinjkt.mobilepqi.R
import com.uinjkt.mobilepqi.common.BaseActivity
import com.uinjkt.mobilepqi.databinding.ActivityDosenSilabusBinding
import com.uinjkt.mobilepqi.util.Constant
import com.uinjkt.mobilepqi.util.openFileManagerPdf
import com.uinjkt.mobilepqi.util.uriToFile
import java.io.File
import org.koin.androidx.viewmodel.ext.android.viewModel

class DosenSilabusActivity : BaseActivity<ActivityDosenSilabusBinding>() {
    companion object {
        @JvmStatic
        fun start(context: Context, idKelas: Int) {
            val starter = Intent(context, DosenSilabusActivity::class.java).putExtra("idKelas", idKelas)
            context.startActivity(starter)
        }
    }

    private val viewModel by viewModel<DosenSilabusViewModel>()
    private val classId by lazy { intent.getIntExtra("idKelas", 0) }
    private var urlSilabus = ""

    override fun getViewBinding(): ActivityDosenSilabusBinding =
        ActivityDosenSilabusBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
        initListener()
        initObserver()
    }

    private fun initView() {
        getSilabus(classId)
    }

    private fun initListener() {
        binding.ivCloseSilabusDosen.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        onBackPressedDispatcher.addCallback(this) {
            finish()
        }

        binding.btnTambahFile.setOnClickListener {
            openFileManagerPdf(launcherIntentFile)
        }

        binding.btnSimpanSilabusDosen.setOnClickListener {
            createSilabus(classId)
            showOneActionDialogWithInvoke(
                message = getString(R.string.tv_tambah_file_silabus_dialog),
                btnMessage = getString(R.string.btn_oke_text),
                onButtonClicked = { getSilabus(classId) }
            )
        }

        binding.btnHapusFile.setOnClickListener {
            showTwoActionDialog(
                message = getString(R.string.tv_hapus_file_silabus_dialog),
                btnPositiveMessage = getString(R.string.btn_oke_text),
                btnNegativeMessage = getString(R.string.btn_batal_text),
                onPositiveButtonClicked = { deleteSilabus(classId) }
            )
        }
    }

    private fun initObserver() {
        viewModel.fileUploaded.observe(this) { model ->
            when (model) {
                is Resource.Loading -> {
                    showLoading(true)
                }
                is Resource.Success -> {
                    showLoading(false)
                    model.data?.fileUrl?.let {
                        urlSilabus = it
                        with(binding) {
                            btnSimpanSilabusDosen.isEnabled = true
                            tvNamaDokumenSilabus.text =
                                urlSilabus.substring(it.lastIndexOf('/') + 1)
                            tvNamaDokumenSilabus.gravity = Gravity.START
                            ivLogoDocument.isGone = false
                        }
                    }
                }
                is Resource.Error -> {
                    showLoading(false)
                    showToast(model.message ?: "Something Went Wrong")
                }
            }
        }

        viewModel.createSilabus.observe(this) { model ->
            when (model) {
                is Resource.Loading -> {
                    showLoading(true)
                }
                is Resource.Success -> {
                    showLoading(false)
                    getSilabus(classId)
                    with(binding) {
                        btnTambahFile.isEnabled = false
                        btnSimpanSilabusDosen.isEnabled = false
                        btnHapusFile.isEnabled = true
                    }
                }
                is Resource.Error -> {
                    showLoading(false)
                    showToast(model.message ?: "Something Went Wrong")
                }
            }
        }

        viewModel.getSilabus.observe(this) { model ->
            when (model) {
                is Resource.Loading -> {
                    showLoading(true)
                }
                is Resource.Success -> {
                    showLoading(false)
                    if (model.data?.silabus?.isNotEmpty() == true) {
                        val url = model.data?.silabus ?: ""
                        with(binding) {
                            tvNamaDokumenSilabus.text = url.substring(url.lastIndexOf('/') + 1)
                            tvNamaDokumenSilabus.gravity = Gravity.START
                            ivLogoDocument.isGone = false
                            btnSimpanSilabusDosen.isEnabled = false
                            btnHapusFile.isEnabled = true
                            btnTambahFile.isEnabled = false
                        }
                    } else {
                        with(binding) {
                            tvNamaDokumenSilabus.text =
                                getString(R.string.tv_empty_silabus_text)
                            tvNamaDokumenSilabus.gravity = Gravity.CENTER
                            ivLogoDocument.isGone = true
                            btnSimpanSilabusDosen.isEnabled = false
                            btnTambahFile.isEnabled = true
                            btnHapusFile.isEnabled = false
                        }
                    }
                }
                is Resource.Error -> {
                    showLoading(false)
                    showToast(model.message ?: "Something Went Wrong")
                }
            }
        }

        viewModel.deleteSilabus.observe(this) { model ->
            when (model) {
                is Resource.Loading -> {
                    showLoading(true)
                }
                is Resource.Success -> {
                    showLoading(false)
                    getSilabus(classId)
                    urlSilabus = ""
                    with(binding) {
                        btnTambahFile.isEnabled = true
                        btnSimpanSilabusDosen.isEnabled = false
                        btnHapusFile.isEnabled = false
                    }
                }
                is Resource.Error -> {
                    showLoading(false)
                    showToast(model.message ?: "Something Went Wrong")
                }
            }
        }
    }


    private val launcherIntentFile = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val selectedFile: Uri = result.data?.data as Uri
            val myFile = uriToFile(selectedFile, this, "pdf")
            viewModel.uploadFilePDF(Constant.UPLOAD_KEY.SILABUS, Constant.UPLOAD_TYPE.FILE, myFile)
        }
    }

    private fun createSilabus(idKelas: Int) {
        viewModel.createSilabus(
            CreateSilabusPayload(
                silabus = urlSilabus
            ),
            idKelas
        )
    }

    private fun getSilabus(idKelas: Int) {
        viewModel.getSilabus(idKelas)
    }

    private fun deleteSilabus(idKelas: Int) {
        viewModel.deleteSilabus(idKelas)
    }

    private fun showLoading(value: Boolean) {
        binding.progressBar.isVisible = value
        binding.btnSimpanSilabusDosen.isEnabled = !value
    }

    override fun onDestroy() {
        if (!isChangingConfigurations) {
            externalCacheDir?.let { deleteTempFile(it) }
        }
        super.onDestroy()
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