package com.uinjkt.mobilepqi.ui.dosen.menuqiroah

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.addCallback
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.domain.model.menuqiroah.GetDetailMateriQiroahModel
import com.uinjkt.mobilepqi.R
import com.uinjkt.mobilepqi.common.BaseActivity
import com.uinjkt.mobilepqi.databinding.ActivityDosenMateriDetailBinding
import com.uinjkt.mobilepqi.ui.mahasiswa.MahasiswaFileUploadedByAdapterList
import org.koin.androidx.viewmodel.ext.android.viewModel

class DosenMateriDetailQiroahActivity : BaseActivity<ActivityDosenMateriDetailBinding>(), MahasiswaFileUploadedByAdapterList.OnUserClickListener {

    private lateinit var fileUploadedByDosenAdapter: MahasiswaFileUploadedByAdapterList
    private val viewModel by viewModel<DosenMateriDetailQiroahViewModel>()
    private lateinit var listFileAttached: List<GetDetailMateriQiroahModel.FileItem>

    companion object {
        @JvmStatic
        fun start(context: Context, id: Int) {
            val starter = Intent(context, DosenMateriDetailQiroahActivity::class.java)
                .putExtra(ID, id)
            context.startActivity(starter)
        }
        private const val ID = "id"
    }

    override fun getViewBinding(): ActivityDosenMateriDetailBinding = ActivityDosenMateriDetailBinding.inflate(layoutInflater)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getDetailMateriQiroah()
        initView()
        initListener()
        initObserver()
    }

    private fun getDetailMateriQiroah() {
        val idMateriDetailQiroah = intent.getIntExtra(ID, 1)
        viewModel.getDetailMateriQiroah(idMateriDetailQiroah)
    }

    private fun initView() {
        binding.rvFileUploadByDosen.layoutManager = LinearLayoutManager(this)
    }

    private fun initListener() {
        binding.ivLogoBackCircleButtonDosen.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        onBackPressedDispatcher.addCallback(this) {
            finish()
        }

        binding.btnSimpanMateriDosen.setOnClickListener {
            showOneActionDialog("Materi Berhasil Disimpan", "Okay")
        }

        binding.btnHapusMateriDosen.setOnClickListener {
            showTwoActionDialog(
                "Yakin Hapus Materi?",
                "Hapus Materi",
                true,
                "Ya",
                "Tidak",
                onPositiveButtonClicked = {
                    showOneActionDialogWithInvoke("Materi Berhasil Dihapus", "Okay") {
                        onBackPressedDispatcher.onBackPressed()
                    }

                })
        }
    }

    private fun initObserver() {
        viewModel.getDetailMateri.observe(this) { model ->
            when (model) {
                is Resource.Loading -> {
                    showToast("loading")
                }
                is Resource.Success -> {
                    model.data?.let {
                        actionAfterGetMateri(it)
                    }
                }
                is Resource.Error -> {
                    showToast(model.message ?: "")
                }
            }
        }
    }

    private fun actionAfterGetMateri(materi: GetDetailMateriQiroahModel) {
        listFileAttached = materi.file
        // Initialize Adapter List Tugas Upload By Dosen
        fileUploadedByDosenAdapter = MahasiswaFileUploadedByAdapterList(this, listFileAttached, "delete",this)
        binding.rvFileUploadByDosen.adapter = fileUploadedByDosenAdapter

        binding.tvTitleMenuDetailDosen.text = getString(R.string.tv_title_detail_materi_qiroah, materi.title)
        binding.etDescDetailMateriDosen.setText(materi.description)
    }

    override fun onUserClickListener(action: String) {
        if (action == "delete") {
            showToast("File Deleted", Toast.LENGTH_SHORT)
        } else {
            showToast("File Downloaded", Toast.LENGTH_SHORT)
        }
    }

}