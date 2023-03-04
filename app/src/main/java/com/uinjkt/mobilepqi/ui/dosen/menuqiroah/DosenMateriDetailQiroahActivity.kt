package com.uinjkt.mobilepqi.ui.dosen.menuqiroah

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.addCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.data.source.remote.response.menuqiroah.UpdateDetailMateriQiroahPayload
import com.mobilepqi.core.domain.model.menuqiroah.GetDetailMateriQiroahModel
import com.uinjkt.mobilepqi.R
import com.uinjkt.mobilepqi.common.BaseActivity
import com.uinjkt.mobilepqi.databinding.ActivityDosenMateriDetailBinding
import com.uinjkt.mobilepqi.ui.mahasiswa.MahasiswaFileUploadedByAdapterList
import com.uinjkt.mobilepqi.util.Constant
import com.uinjkt.mobilepqi.util.openFileManagerPdf
import com.uinjkt.mobilepqi.util.openGallery
import com.uinjkt.mobilepqi.util.uriToFile
import java.io.File
import org.koin.androidx.viewmodel.ext.android.viewModel

class DosenMateriDetailQiroahActivity : BaseActivity<ActivityDosenMateriDetailBinding>(), MahasiswaFileUploadedByAdapterList.OnUserClickListener {

    companion object {
        @JvmStatic
        fun start(context: Context, id: Int) {
            val starter = Intent(context, DosenMateriDetailQiroahActivity::class.java)
                .putExtra(ID, id)
            context.startActivity(starter)
        }
        private const val ID = "id"
    }

    private val viewModel by viewModel<DosenMateriDetailQiroahViewModel>()
    private val idMateri by lazy { intent.getIntExtra(ID, 0) }
    private var urlFile = ""
    private lateinit var fileUploadedByDosenAdapter: MahasiswaFileUploadedByAdapterList
    private lateinit var listFileAttached: MutableList<GetDetailMateriQiroahModel.FileItem>

    override fun getViewBinding(): ActivityDosenMateriDetailBinding = ActivityDosenMateriDetailBinding.inflate(layoutInflater)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initListener()
        initObserver()
    }

    private fun initView() {
        getDetailMateriQiroah()
    }

    private fun getDetailMateriQiroah() {
        viewModel.getDetailMateriQiroah(idMateri)
    }


    private fun initListener() {

        with(binding) {
            ivLogoBackCircleButtonDosen.setOnClickListener {
                onBackPressedDispatcher.onBackPressed()
            }

            ivAttachFile.setOnClickListener {
                openFileManagerPdf(launcherIntentFile)
            }

            ivInsertLink.setOnClickListener {
                // TODO("Masukkan Link")
            }

            ivImageAttach.setOnClickListener {
                openGallery(launcherIntentGallery)
            }

            ivMicAttach.setOnClickListener {
                // TODO("Rekam Suara")
            }

            btnSimpanMateriDosen.setOnClickListener {
                updateDetailMaterQiroah(listFileAttached, etDescDetailMateriDosen.text?.toString() ?:"", idMateri)
            }

            btnHapusMateriDosen.setOnClickListener {
                showTwoActionDialog(
                    "Yakin Hapus Materi?",
                    "Hapus Materi",
                    true,
                    "Ya",
                    "Tidak",
                    onPositiveButtonClicked = {
                        deleteMateriQiroah(idMateri)
                    })
            }
        }
    }

    private fun updateDetailMaterQiroah(file: List<GetDetailMateriQiroahModel.FileItem>, description: String, idMateri: Int) {
        viewModel.updateDetailMateriQiroah(
            UpdateDetailMateriQiroahPayload(
                file = file.map { fileItem ->
                    UpdateDetailMateriQiroahPayload.FileItem(
                        url = fileItem.url
                    )
                },
                description = description
            ),
            idMateri)
    }

    private fun deleteMateriQiroah(idMateri: Int) {
        viewModel.deletemateriQiroah(idMateri)
    }

    private fun initObserver() {
        viewModel.getDetailMateri.observe(this) { model ->
            when (model) {
                is Resource.Loading -> {
                    showLoading(true)
                }
                is Resource.Success -> {
                    model.data?.let {
                        actionAfterGetMateri(it)
                    }
                    showLoading(false)
                }
                is Resource.Error -> {
                    showToast(model.message ?: "Something Went Wrong")
                    showLoading(false)
                }
            }
        }

        viewModel.fileUploaded.observe(this) { model ->
            when(model) {
                is Resource.Loading -> {
                    binding.pbFileLoading.isVisible = true
                }
                is Resource.Success -> {
                    model.data?.fileUrl?.let {
                        urlFile = it
                        listFileAttached.add(0,GetDetailMateriQiroahModel.FileItem(urlFile))
                        fileUploadedByDosenAdapter.setData(listFileAttached)
                    }
                    binding.pbFileLoading.isVisible = false
                }
                is Resource.Error -> {
                    showToast(model.message ?: "Something Went Wrong")
                    binding.pbFileLoading.isVisible = false
                }
            }
        }

        viewModel.updateDetailMateri.observe(this) { model ->
            when(model) {
                is Resource.Loading -> {
                    binding.pbLoadingScreen.isVisible = true
                }
                is Resource.Success -> {
                    binding.pbLoadingScreen.isVisible = false
                    showOneActionDialogWithInvoke("Materi Berhasil Disimpan", "Okay") {
                        onBackPressedDispatcher.onBackPressed()
                    }
                }
                is Resource.Error -> {
                    showToast(model.message ?: "Something Went Wrong")
                    binding.pbLoadingScreen.isVisible = false
                }
            }
        }

        viewModel.deleteMateri.observe(this) { model ->
            when(model) {
                is Resource.Loading -> {
                    showLoading(true)
                }
                is Resource.Success -> {
                    showLoading(false)
                    showOneActionDialogWithInvoke("Materi Berhasil Dihapus", "Okay") {
                        onBackPressedDispatcher.onBackPressed()
                    }
                }
                is Resource.Error -> {
                    showToast(model.message ?: "Something Went Wrong")
                    showLoading(false)
                }
            }
        }

        onBackPressedDispatcher.addCallback(this) {
            finish()
        }
    }


    private fun showLoading(value: Boolean) {
        binding.pbLoadingScreen.isVisible = value
        binding.nsvContentDetail.isVisible = !value
    }

    private fun actionAfterGetMateri(materi: GetDetailMateriQiroahModel) {
        listFileAttached = materi.file.toMutableList()
        // Initialize Adapter List Tugas Upload By Dosen
        initAdapter()
        binding.tvTitleMenuDetailDosen.text = getString(R.string.tv_title_detail_materi_qiroah, materi.title)
        binding.etDescDetailMateriDosen.setText(materi.description)
    }

    private fun initAdapter() {
        fileUploadedByDosenAdapter = MahasiswaFileUploadedByAdapterList(this, listFileAttached.toMutableList(), "delete",this)
        binding.rvFileUploadByDosen.adapter = fileUploadedByDosenAdapter
        binding.rvFileUploadByDosen.layoutManager = LinearLayoutManager(this)
    }

    private val launcherIntentFile = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val selectedFile: Uri = result.data?.data as Uri
            val myFile = uriToFile(selectedFile, this, "pdf")
            viewModel.uploadFileOrImage(Constant.UPLOAD_KEY.MATERI, Constant.UPLOAD_TYPE.FILE, myFile)
        }
    }

    private val launcherIntentGallery = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val selectedFile: Uri = result.data?.data as Uri
            val myFile = uriToFile(selectedFile, this, "image")
            viewModel.uploadFileOrImage(Constant.UPLOAD_KEY.MATERI, Constant.UPLOAD_TYPE.IMAGE, myFile)
        }
    }

    override fun onUserClickListener(action: String, position: Int) {
        if (action == "delete") {
            listFileAttached.removeAt(position)
            fileUploadedByDosenAdapter.setData(listFileAttached)
        } else {
            showToast("File Downloaded", Toast.LENGTH_SHORT)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (!isChangingConfigurations) {
            externalCacheDir?.let { deleteTempFile(it) }
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