package com.uinjkt.mobilepqi.ui.mahasiswa.menuibadah

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.addCallback
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.domain.model.common.FileItem
import com.mobilepqi.core.domain.model.menuibadah.GetDetailMateriIbadahModel
import com.uinjkt.mobilepqi.R
import com.uinjkt.mobilepqi.common.BaseActivity
import com.uinjkt.mobilepqi.databinding.ActivityMahasiswaMateriDetailBinding
import com.uinjkt.mobilepqi.ui.mahasiswa.MahasiswaFileUploadedByAdapterList
import com.uinjkt.mobilepqi.util.capitalizeEachWord
import com.uinjkt.mobilepqi.util.downloadFileToStorage
import com.uinjkt.mobilepqi.util.getFileNameFromUrl
import org.koin.androidx.viewmodel.ext.android.viewModel

class MahasiswaMateriDetailIbadahActivity : BaseActivity<ActivityMahasiswaMateriDetailBinding>(), MahasiswaFileUploadedByAdapterList.OnUserClickListener {

    companion object {
        @JvmStatic
        fun start(context: Context, idMateri: Int) {
            val starter = Intent(context, MahasiswaMateriDetailIbadahActivity::class.java)
                .putExtra(ID, idMateri)
            context.startActivity(starter)
        }

        private const val ID = "idMateri"
    }

    private lateinit var fileUploadedByDosenAdapter: MahasiswaFileUploadedByAdapterList
    private val viewModel by viewModel<MahasiswaMateriDetailIbadahViewModel>()
    private lateinit var listFileAttached: List<FileItem>

    override fun getViewBinding(): ActivityMahasiswaMateriDetailBinding = ActivityMahasiswaMateriDetailBinding.inflate(layoutInflater)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initListener()
        initObserver()
    }

    private fun initView() {
        val breadcrumbText = getString(R.string.tv_title_menu_materi_detail, "Materi Ibadah", "Materi Detail Ibadah")
        binding.tvTitleMenuDetail.text = breadcrumbText
        getDetailMateriIbadah()
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

    private fun actionAfterGetMateri(materi: GetDetailMateriIbadahModel) {
        listFileAttached = materi.file.map {
            FileItem(
                url = it.url
            )
        }
        initAdapter()
        binding.tvTitleMenuDetail.text = getString(R.string.tv_title_detail_materi_ibadah, materi.title.capitalizeEachWord())
        checkEmptyDescription(materi.description)
    }

    private fun checkEmptyDescription(description: String) {
        if (description.isEmpty() || description.isBlank()) {
            binding.tvDescriptionMenuDetail.text = getString(R.string.description_empty_state, "Materi")
        } else {
            binding.tvDescriptionMenuDetail.text = description
        }
    }

    private fun initAdapter() {
        fileUploadedByDosenAdapter = MahasiswaFileUploadedByAdapterList(this, listFileAttached.toMutableList(), "download",this)
        binding.rvFileUploadByDosen.adapter = fileUploadedByDosenAdapter
        binding.rvFileUploadByDosen.layoutManager = LinearLayoutManager(this)
    }

    private fun showLoading(value: Boolean) {
        binding.pbLoadingScreen.isVisible = value
        binding.nsvContentDetail.isVisible = !value
    }

    private fun getDetailMateriIbadah() {
        viewModel.getDetailMateriIbadah(intent.getIntExtra(ID, 0))
    }

    override fun onUserClickListener(action: String, position: Int) {
        if(action == "download") {
            val urlFile = listFileAttached[position].url
            downloadFileToStorage(this, urlFile, urlFile.getFileNameFromUrl())
        }
    }
}