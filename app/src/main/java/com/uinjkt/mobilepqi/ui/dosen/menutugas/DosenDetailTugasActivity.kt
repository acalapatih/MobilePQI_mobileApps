package com.uinjkt.mobilepqi.ui.dosen.menutugas

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.addCallback
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.domain.model.common.FileItem
import com.mobilepqi.core.domain.model.tugas.GetDetailTugasModel
import com.uinjkt.mobilepqi.R
import com.uinjkt.mobilepqi.common.BaseActivity
import com.uinjkt.mobilepqi.databinding.ActivityDosenDetailTugasBinding
import com.uinjkt.mobilepqi.ui.mahasiswa.MahasiswaFileUploadedByAdapterList
import com.uinjkt.mobilepqi.util.capitalizeEachWord
import com.uinjkt.mobilepqi.util.downloadFileToStorage
import com.uinjkt.mobilepqi.util.getFileNameFromUrl
import org.koin.androidx.viewmodel.ext.android.viewModel

class DosenDetailTugasActivity : BaseActivity<ActivityDosenDetailTugasBinding>(),
    MahasiswaFileUploadedByAdapterList.OnUserClickListener {

    companion object {
        @JvmStatic
        fun start(context: Context, idTugas: Int) {
            val starter = Intent(context, DosenDetailTugasActivity::class.java)
                .putExtra(ID_TUGAS, idTugas)
            context.startActivity(starter)
        }

        private const val ID_TUGAS = "topic"
    }

    private val idTugas by lazy { intent.getIntExtra(ID_TUGAS, 0) }

    private val viewModel by viewModel<DosenDetailTugasViewModel>()

    private lateinit var listFileAttached: MutableList<FileItem>
    private lateinit var fileUploadedByDosenAdapter: MahasiswaFileUploadedByAdapterList

    override fun getViewBinding(): ActivityDosenDetailTugasBinding =
        ActivityDosenDetailTugasBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initListener()
        initObserver()

    }

    private fun initView() {
        binding.tvTitleTugasDetailMahasiswa.text =
            getString(R.string.tv_title_tugas_detail_mahasiswa, "")
        binding.tvTenggatWaktuTugasDosen.text = getString(R.string.tv_tenggat_waktu_tugas, "")
        listFileAttached = mutableListOf()
        getDetailTugas()
    }

    private fun getDetailTugas() {
        viewModel.getDetailTugas(idTugas)
    }

    private fun initListener() {
        binding.ivLogoBackCircleButtonTugasDosen.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
        onBackPressedDispatcher.addCallback(this) {
            finish()
        }

        binding.btnEditTugasDosen.setOnClickListener {
            DosenBuatEditTugasActivity.start(this@DosenDetailTugasActivity, "edit",  idTugas = idTugas)
        }

        binding.btnCekTugasMahasiswa.setOnClickListener {
            DosenCekTugasMahasiswaActivity.start(this@DosenDetailTugasActivity, idTugas)
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
    }

    private fun actionAfterGetDetailTugas(model: GetDetailTugasModel) {
        val title = model.title.capitalizeEachWord()
        binding.tvTitleTugasDetailMahasiswa.text =
            getString(R.string.tv_title_tugas_detail_mahasiswa,
                "${model.topic.capitalizeEachWord()} > $title")
        binding.tvNamaTaskTugas.text = title
        binding.tvTenggatWaktuTugasDosen.text =
            getString(R.string.tv_tenggat_waktu_tugas, model.deadline.substring(0, 10))
        binding.tvDescriptionTugasDetail.text = model.description
        listFileAttached.addAll(0, model.file)
        initAdapter()
    }

    private fun initAdapter() {
        fileUploadedByDosenAdapter =
            MahasiswaFileUploadedByAdapterList(this, listFileAttached, "download", this)
        binding.rvFileUploadByDosen.adapter = fileUploadedByDosenAdapter
        binding.rvFileUploadByDosen.layoutManager = LinearLayoutManager(this)
    }

    private fun showloading(value: Boolean) {
        binding.nsvContentDetail.isVisible = !value
        binding.pbLoadingScreen.isVisible = value
    }

    override fun onUserClickListener(action: String, position: Int) {
        val url = listFileAttached[position].url
        if (action == "download") {
            downloadFileToStorage(this, url, url.getFileNameFromUrl())
        }
    }

    override fun onRestart() {
        super.onRestart()
        initView()
    }

}