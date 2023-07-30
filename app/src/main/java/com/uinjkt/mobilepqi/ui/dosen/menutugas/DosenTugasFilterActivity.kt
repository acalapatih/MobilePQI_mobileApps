package com.uinjkt.mobilepqi.ui.dosen.menutugas

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.addCallback
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.domain.model.common.JenisTugas
import com.mobilepqi.core.domain.model.tugas.GetListTopicTugasModel
import com.uinjkt.mobilepqi.R
import com.uinjkt.mobilepqi.common.BaseActivity
import com.uinjkt.mobilepqi.databinding.ActivityDosenTugasFilteredBinding
import com.uinjkt.mobilepqi.ui.mahasiswa.ListMahasiswaTugasAdapterList
import com.uinjkt.mobilepqi.ui.mahasiswa.MenuMahasiswaJenisTugasAdapterNew
import com.uinjkt.mobilepqi.util.downloadFileToStorage
import com.uinjkt.mobilepqi.util.getFileNameFromUrl
import org.koin.androidx.viewmodel.ext.android.viewModel

class DosenTugasFilterActivity : BaseActivity<ActivityDosenTugasFilteredBinding>(),
    ListMahasiswaTugasAdapterList.OnUserClickTugasListener,
    MenuMahasiswaJenisTugasAdapterNew.OnUserClickJenisTugasListener {

    companion object {
        @JvmStatic
        fun start(context: Context, idKelas: Int, topic: String) {
            val starter = Intent(context, DosenTugasFilterActivity::class.java)
                .putExtra(ID_KELAS, idKelas)
                .putExtra(TOPIC, topic)
            context.startActivity(starter)
        }

        private const val ID_KELAS = "idKelas"
        private const val TOPIC = "topic"
    }

    private val listJenisTugas by lazy {
        mutableListOf(
            JenisTugas("Praktikum Qiroah", false),
            JenisTugas("Praktikum Ibadah", false),
            JenisTugas("Hafalan Surah", false),
            JenisTugas("Hafalan Doa", false),
        )
    }

    private val idKelas by lazy { intent.getIntExtra(ID_KELAS, 0) }
    private var titleTopic: String? = null

    private lateinit var dosenJenisTugasAdapter: MenuMahasiswaJenisTugasAdapterNew
    private lateinit var dosenTugasFilterAdapter: ListMahasiswaTugasAdapterList
    private val viewModel by viewModel<DosenTugasFilterViewModel>()
    private lateinit var topic: String
    private var idxTopic = 0

    override fun getViewBinding(): ActivityDosenTugasFilteredBinding =
        ActivityDosenTugasFilteredBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        titleTopic = intent.getStringExtra(TOPIC)
        topic = getSelectedTopic(titleTopic ?: "all")
        initView()
        initListener()
        initObserver()
    }

    private fun getSelectedTopic(title: String): String {
        binding.tvTugasFilter.text = title
        binding.tvTitleJenisTugasDosen.text =
            getString(R.string.tv_title_jenis_tugas_mahasiswa, title)
        when (title) {
            "Praktikum Qiroah" -> {
                changeStatusSelected(0)
                idxTopic = 0
                return "qiroah"
            }
            "Praktikum Ibadah" -> {
                changeStatusSelected(1)
                idxTopic = 1
                return "ibadah"
            }
            "Hafalan Surah" -> {
                changeStatusSelected(2)
                idxTopic = 2
                return "surat"
            }
            "Hafalan Doa" -> {
                changeStatusSelected(3)
                idxTopic = 3
                return "doa"
            }
            else -> {
                return "all"
            }
        }
    }


    private fun initView() {
        initJenisTugasAdapter(idxTopic)
        getListTugas()
    }

    private fun getListTugas() {
        viewModel.getListTopicTugas(idKelas, topic)
    }

    private fun initJenisTugasAdapter(position: Int) {
        dosenJenisTugasAdapter = MenuMahasiswaJenisTugasAdapterNew(this, listJenisTugas, this)
        binding.rvJenisTugasDosenFilter.adapter = dosenJenisTugasAdapter
        binding.rvJenisTugasDosenFilter.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvJenisTugasDosenFilter.scrollToPosition(position)
    }

    private fun initListener() {
        binding.ivLogoBackCircleButtonTugasDosenFilter.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding.tvIconUnduhNilai.setOnClickListener {
            showTwoActionDialog(
                "Unduh Nilai?",
                btnPositiveMessage = "Unduh",
                btnNegativeMessage = "Batal"
            ) {
                downloadNilai()
            }
        }

        onBackPressedDispatcher.addCallback(this) {
            finish()
        }
    }

    private fun downloadNilai() {
        viewModel.downloadNilai(idKelas, topic)
    }

    private fun initObserver() {
        viewModel.getListTopicTugas.observe(this) { model ->
            when (model) {
                is Resource.Loading -> {
                    showLoading(true)
                }
                is Resource.Success -> {
                    model.data?.let {
                        actionAfterGetListTopicTugas(it)
                    }
                    showLoading(false)
                }
                is Resource.Error -> {
                    showToast(model.message ?: "Something Went Wrong")
                    showLoading(false)
                }
            }
        }
        viewModel.downloadNilai.observe(this) { model ->
            when (model) {
                is Resource.Loading -> {
                    showLoading(true)
                }
                is Resource.Success -> {
                    model.data?.let {
                        showOneActionDialogWithInvoke("Unduh Nilai Berhasil", "Okay") {
                            downloadFileToStorage(this, it.url, it.url.getFileNameFromUrl())
                        }
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
        binding.nsvTugas.isVisible = !value
    }

    private fun actionAfterGetListTopicTugas(model: GetListTopicTugasModel) {
        if(model.tugas.isNullOrEmpty()) {
            showEmptyState(true)
        } else {
            showEmptyState(false)
        }

        initListTugasAdapter(model)
    }

    private fun showEmptyState(value: Boolean) {
        if (value) {
            binding.tvBelumAdaTugasQiroahFilter.text =
                getString(R.string.tv_belum_ada_tugas, titleTopic)
        }
        binding.tvBelumAdaTugasQiroahFilter.isVisible = value
    }

    private fun initListTugasAdapter(model: GetListTopicTugasModel) {
        // Initialize Adapter Tugas Filter
        dosenTugasFilterAdapter =
            ListMahasiswaTugasAdapterList(this, model.tugas ?: emptyList(), this)
        binding.rvListTugasDosenFilter.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvListTugasDosenFilter.adapter = dosenTugasFilterAdapter

    }

    override fun onUserTugasClicked(idTugas: Int) {
        DosenDetailTugasActivity.start(this@DosenTugasFilterActivity, idTugas)
    }

    override fun onUserJenisTugasClicked(data: JenisTugas) {
        val title = data.titleJenisTugas
        binding.tvTitleJenisTugasDosen.text = title
        binding.tvTugasFilter.text = title
        titleTopic = title
        topic = getSelectedTopic(title)
        dosenJenisTugasAdapter.changeSelected(idxTopic)
        getListTugas()
    }

    private fun changeStatusSelected(index: Int) {
        for (i in listJenisTugas.indices) {
            when (i) {
                index -> {
                    listJenisTugas[i].isSelected = true
                }
                else -> listJenisTugas[i].isSelected = false
            }
        }
    }
}