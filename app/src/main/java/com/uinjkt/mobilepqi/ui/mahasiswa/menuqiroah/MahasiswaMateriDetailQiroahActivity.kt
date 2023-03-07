package com.uinjkt.mobilepqi.ui.mahasiswa.menuqiroah

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.addCallback
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.domain.model.common.FileItem
import com.mobilepqi.core.domain.model.menuqiroah.GetDetailMateriQiroahModel
import com.uinjkt.mobilepqi.R
import com.uinjkt.mobilepqi.common.BaseActivity
import com.uinjkt.mobilepqi.databinding.ActivityMahasiswaMateriDetailBinding
import com.uinjkt.mobilepqi.ui.mahasiswa.MahasiswaFileUploadedByAdapterList
import com.uinjkt.mobilepqi.util.downloadFileToStorage
import com.uinjkt.mobilepqi.util.getFileNameFromUrl
import org.koin.androidx.viewmodel.ext.android.viewModel

class MahasiswaMateriDetailQiroahActivity : BaseActivity<ActivityMahasiswaMateriDetailBinding>(), MahasiswaFileUploadedByAdapterList.OnUserClickListener {

    companion object {
        @JvmStatic
        fun start(context: Context, idMateri: Int) {
            val starter = Intent(context, MahasiswaMateriDetailQiroahActivity::class.java)
                .putExtra(ID, idMateri)
            context.startActivity(starter)
        }
        private const val ID = "idMateri"
    }

    private lateinit var fileUploadedByDosenAdapter: MahasiswaFileUploadedByAdapterList
    private val viewModel by viewModel<MahasiswaMateriDetailQiroahViewModel>()
    private lateinit var listFileAttached: List<FileItem>

    override fun getViewBinding(): ActivityMahasiswaMateriDetailBinding = ActivityMahasiswaMateriDetailBinding.inflate(layoutInflater)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initListener()
        initObserver()
    }

    private fun getDetailMateriQiroah() {
        viewModel.getDetailMateriQiroah(intent.getIntExtra(ID, 0))
    }

    private fun initView() {
        getDetailMateriQiroah()
    }

    private fun initListener() {
        binding.ivLogoBackCircleButton.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        onBackPressedDispatcher.addCallback(this) {
            finish()
        }
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
    }

    private fun showLoading(value: Boolean) {
        binding.pbLoadingScreen.isVisible = value
        binding.nsvContentDetail.isVisible = !value
    }

    private fun actionAfterGetMateri(materi: GetDetailMateriQiroahModel) {
        listFileAttached = materi.file.map {
            FileItem(
                url = it.url
            )
        }
        initAdapter()
        binding.tvTitleMenuDetail.text = getString(R.string.tv_title_detail_materi_qiroah, materi.title)
        binding.tvDescriptionMenuDetail.text = materi.description
    }

    private fun initAdapter() {
        fileUploadedByDosenAdapter = MahasiswaFileUploadedByAdapterList(this, listFileAttached.toMutableList(), "download",this)
        binding.rvFileUploadByDosen.adapter = fileUploadedByDosenAdapter
        binding.rvFileUploadByDosen.layoutManager = LinearLayoutManager(this)
    }

    override fun onUserClickListener(action: String, position: Int) {
        if(action == "download") {
            val urlFile = listFileAttached[position].url
            downloadFileToStorage(this, urlFile, urlFile.getFileNameFromUrl())
        }
    }
}