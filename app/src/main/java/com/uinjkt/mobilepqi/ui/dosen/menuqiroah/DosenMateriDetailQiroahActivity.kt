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
import com.uinjkt.mobilepqi.util.uriToFile
import org.koin.androidx.viewmodel.ext.android.viewModel

class DosenMateriDetailQiroahActivity : BaseActivity<ActivityDosenMateriDetailBinding>(), MahasiswaFileUploadedByAdapterList.OnUserClickListener {

    private lateinit var fileUploadedByDosenAdapter: MahasiswaFileUploadedByAdapterList
    private lateinit var listFileAttached: MutableList<GetDetailMateriQiroahModel.FileItem>
    private val viewModel by viewModel<DosenMateriDetailQiroahViewModel>()
    private val idMateri by lazy { intent.getIntExtra(ID, 0) }

    companion object {
        @JvmStatic
        fun start(context: Context, id: Int) {
            val starter = Intent(context, DosenMateriDetailQiroahActivity::class.java)
                .putExtra(ID, id)
            context.startActivity(starter)
        }
        private const val ID = "id"
    }

    private var urlFile = ""

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

            btnSimpanMateriDosen.setOnClickListener {
                showOneActionDialogWithInvoke("Materi Berhasil Disimpan", "Okay") {
                    updateDetailMaterQiroah(listFileAttached, etDescDetailMateriDosen.text?.toString() ?:"", idMateri)
                    onBackPressedDispatcher.onBackPressed()
                }
            }

            btnHapusMateriDosen.setOnClickListener {
                showTwoActionDialog(
                    "Yakin Hapus Materi?",
                    "Hapus Materi",
                    true,
                    "Ya",
                    "Tidak",
                    onPositiveButtonClicked = {
                        showOneActionDialogWithInvoke("Materi Berhasil Dihapus", "Okay") {
                            deleteMateriQiroah(idMateri)
                            onBackPressedDispatcher.onBackPressed()
                        }

                    })
            }
        }

        onBackPressedDispatcher.addCallback(this) {
            finish()
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
                    showFileLoading(true)
                }
                is Resource.Success -> {
                    model.data?.fileUrl?.let {
                        urlFile = it
                        listFileAttached.add(GetDetailMateriQiroahModel.FileItem(urlFile))
                        fileUploadedByDosenAdapter.setData(listFileAttached)
                    }
                    showFileLoading(false)
                }
                is Resource.Error -> {
                    showToast(model.message ?: "Something Went Wrong")
                    showFileLoading(false)
                }
            }
        }

        viewModel.updateDetailMateri.observe(this) { model ->
            when(model) {
                is Resource.Loading -> {
                    showLoading(true)
                }
                is Resource.Success -> {
                    showLoading(false)
                }
                is Resource.Error -> {
                    showToast(model.message ?: "Something Went Wrong")
                    showLoading(false)
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
                }
                is Resource.Error -> {
                    showToast(model.message ?: "Something Went Wrong")
                    showLoading(false)
                }
            }
        }
    }

    private fun showFileLoading(value: Boolean) {
        binding.pbFileLoading.isVisible = value
        binding.rvFileUploadByDosen.isVisible = !value
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
            viewModel.uploadFilePDF(Constant.UPLOAD_KEY.MATERI, Constant.UPLOAD_TYPE.FILE, myFile)
        }
    }

    private val launcherIntentGallery = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val selectedFile: Uri = result.data?.data as Uri

            val myFile = uriToFile(selectedFile, this, "image")

            /*
            TODO
            trigger viewmodel disini, parameter file masukin aja myFile
             */
        }
    }

    override fun onUserClickListener(action: String, position: Int) {
        if (action == "delete") {
            listFileAttached.removeAt(position)
//            Log.d("TEST_TEST_TEST", "position : $position \nsize: ${fileUploadedByDosenAdapter.itemCount}")
            fileUploadedByDosenAdapter.setData(listFileAttached)
        } else {
            showToast("File Downloaded", Toast.LENGTH_SHORT)
        }
    }

}