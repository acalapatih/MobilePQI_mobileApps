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
    private val titleTopic by lazy { intent.getStringExtra(TOPIC) }

    private lateinit var dosenJenisTugasAdapter: MenuMahasiswaJenisTugasAdapterNew
    private lateinit var dosenTugasFilterAdapter: ListMahasiswaTugasAdapterList
    private val viewModel by viewModel<DosenTugasFilterViewModel>()
    private lateinit var topic: String

    override fun getViewBinding(): ActivityDosenTugasFilteredBinding =
        ActivityDosenTugasFilteredBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        topic = getSelectedTopic(titleTopic?:"all")
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
                return "qiroah"
            }
            "Praktikum Ibadah" -> {
                changeStatusSelected(1)
                return "ibadah"
            }
            "Hafalan Surah" -> {
                changeStatusSelected(2)
                return "surat"
            }
            "Hafalan Doa" -> {
                changeStatusSelected(3)
                return "doa"
            }
            else -> {
                return "all"
            }
        }
    }


    private fun initView() {
        initJenisTugasAdapter()
        getListTugas()
    }

    private fun getListTugas() {
        viewModel.getListTopicTugas(idKelas, topic)
    }

    private fun initJenisTugasAdapter() {
        dosenJenisTugasAdapter = MenuMahasiswaJenisTugasAdapterNew(this, listJenisTugas, this)
        binding.rvJenisTugasDosenFilter.adapter = dosenJenisTugasAdapter
        binding.rvJenisTugasDosenFilter.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun initListener() {
        binding.ivLogoBackCircleButtonTugasDosenFilter.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding.tvIconUnduhNilai.setOnClickListener {
            showTwoActionDialog("Unduh Nilai?",
                btnPositiveMessage = "Unduh",
                btnNegativeMessage = "batal") {
                showOneActionDialog("Nilai Berhasil Diunduh", "Okay")
            }
        }

        onBackPressedDispatcher.addCallback(this) {
            finish()
        }
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
    }

    private fun showLoading(value: Boolean) {
        binding.pbLoadingScreen.isVisible = value
        binding.nsvTugas.isVisible = !value
    }

    private fun actionAfterGetListTopicTugas(model: GetListTopicTugasModel) {
        initListTugasAdapter(model)
        binding.tvBelumAdaTugasQiroahFilter.text = getString(R.string.tv_belum_ada_tugas, titleTopic)
        binding.tvBelumAdaTugasQiroahFilter.isVisible = model.tugas?.isEmpty() ?:true
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
        topic = getSelectedTopic(title)
        initView()
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