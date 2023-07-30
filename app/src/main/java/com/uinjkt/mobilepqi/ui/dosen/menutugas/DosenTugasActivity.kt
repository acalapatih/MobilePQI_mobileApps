package com.uinjkt.mobilepqi.ui.dosen.menutugas

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.addCallback
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.domain.model.common.JenisTugas
import com.mobilepqi.core.domain.model.tugas.GetListTugasModel
import com.uinjkt.mobilepqi.R
import com.uinjkt.mobilepqi.common.BaseActivity
import com.uinjkt.mobilepqi.databinding.ActivityDosenTugasSemuaBinding
import com.uinjkt.mobilepqi.ui.mahasiswa.ListMahasiswaTugasAdapterList
import com.uinjkt.mobilepqi.ui.mahasiswa.MenuMahasiswaJenisTugasAdapterNew
import com.uinjkt.mobilepqi.util.downloadFileToStorage
import com.uinjkt.mobilepqi.util.getFileNameFromUrl
import org.koin.androidx.viewmodel.ext.android.viewModel

class DosenTugasActivity : BaseActivity<ActivityDosenTugasSemuaBinding>(),
    MenuMahasiswaJenisTugasAdapterNew.OnUserClickJenisTugasListener,
    ListMahasiswaTugasAdapterList.OnUserClickTugasListener {

    companion object {
        @JvmStatic
        fun start(context: Context, idKelas: Int) {
            val starter = Intent(context, DosenTugasActivity::class.java)
                .putExtra(ID_KELAS, idKelas)
            context.startActivity(starter)
        }

        private const val ID_KELAS = "idKelas"
    }

    private val listJenisTugas by lazy {
        mutableListOf(
            JenisTugas("Semua", true),
            JenisTugas("Praktikum Qiroah", false),
            JenisTugas("Praktikum Ibadah", false),
            JenisTugas("Hafalan Surah", false),
            JenisTugas("Hafalan Doa", false),
        )
    }
    private lateinit var dosenJenisTugasAdapter: MenuMahasiswaJenisTugasAdapterNew
    private lateinit var dosenTugasQiroahAdapter: ListMahasiswaTugasAdapterList
    private lateinit var dosenTugasIbadahAdapter: ListMahasiswaTugasAdapterList
    private lateinit var dosenTugasSurahAdapter: ListMahasiswaTugasAdapterList
    private lateinit var dosenTugasDoaAdapter: ListMahasiswaTugasAdapterList
    private val viewModel by viewModel<DosenTugasViewModel>()
    private val idKelas by lazy { intent.getIntExtra(ID_KELAS, 0) }

    override fun getViewBinding(): ActivityDosenTugasSemuaBinding =
        ActivityDosenTugasSemuaBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initListener()
        initObserver()
    }

    private fun initView() {
        // Set Title Action Bar
        binding.tvTitleAppBarDosenSemua.text = getString(R.string.tv_titleappbar_tugas)
        getListTugas(idKelas)
    }

    private fun getListTugas(idKelas: Int) {
        viewModel.getListTugas(idKelas)
    }

    private fun initListener() {
        // icon Close onClickListener
        binding.ivIconClose.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding.tvIconAddTugas.setOnClickListener {
            DosenBuatEditTugasActivity.start(this, "buat", idKelas = idKelas)
        }

        binding.tvIconUnduhNilaiTugas.setOnClickListener {
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
        viewModel.downloadNilai(idKelas)
    }

    private fun initObserver() {
        viewModel.getListTugas.observe(this) { model ->
            when (model) {
                is Resource.Loading -> {
                    showLoading(true)
                }
                is Resource.Success -> {
                    model.data?.let {
                        actionAfterGetListTugas(it)
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

    private fun actionAfterGetListTugas(model: GetListTugasModel) {
        showEmptyStateEachTopic(model)
        initAdapter(model)
    }

    private fun showEmptyStateEachTopic(model: GetListTugasModel) {
        binding.tvBelumAdaTugasPraktikumQiroahDosen.isVisible = model.qiroah.isEmpty()
        binding.tvBelumAdaTugasPraktikumIbadahDosen.isVisible = model.ibadah.isEmpty()
        binding.tvBelumAdaTugasHafalanSurahDosen.isVisible = model.surah.isEmpty()
        binding.tvBelumAdaTugasHafalanDoaDosen.isVisible = model.doa.isEmpty()
    }


    private fun showLoading(value: Boolean) {
        binding.pbLoadingScreen.isVisible = value
        binding.nsvTugas.isVisible = !value
    }

    private fun initAdapter(model: GetListTugasModel) {
        // Initialize Adapter Jenis Tugas
        dosenJenisTugasAdapter = MenuMahasiswaJenisTugasAdapterNew(this, listJenisTugas, this)
        binding.rvJenisTugasDosenSemua.adapter = dosenJenisTugasAdapter
        binding.rvJenisTugasDosenSemua.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        if (model.qiroah.isNotEmpty()) {
            // Initialize Adapter Tugas Qiroah
            dosenTugasQiroahAdapter = ListMahasiswaTugasAdapterList(this, model.qiroah, this)
            binding.rvListTugasDosenQiroah.adapter = dosenTugasQiroahAdapter
            binding.rvListTugasDosenQiroah.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        }

        if (model.ibadah.isNotEmpty()) {
            // Initialize Adapter Tugas Ibadah
            dosenTugasIbadahAdapter = ListMahasiswaTugasAdapterList(this, model.ibadah, this)
            binding.rvListTugasDosenIbadah.adapter = dosenTugasIbadahAdapter
            binding.rvListTugasDosenIbadah.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        }

        if (model.doa.isNotEmpty()) {
            // Initialize Adapter Tugas Hafalan Doa
            dosenTugasDoaAdapter = ListMahasiswaTugasAdapterList(this, model.doa, this)
            binding.rvListTugasDosenHafalanDoa.adapter = dosenTugasDoaAdapter
            binding.rvListTugasDosenHafalanDoa.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        }

        if (model.surah.isNotEmpty()) {
            // Initialize Adapter Tugas Hafalan Surah
            dosenTugasSurahAdapter = ListMahasiswaTugasAdapterList(this, model.surah, this)
            binding.rvListTugasDosenHafalanSurah.adapter = dosenTugasSurahAdapter
            binding.rvListTugasDosenHafalanSurah.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        }
    }

    override fun onRestart() {
        super.onRestart()
        dosenJenisTugasAdapter.changeSelected(0)
        getListTugas(idKelas)
    }

    override fun onUserTugasClicked(idTugas: Int) {
        DosenDetailTugasActivity.start(this@DosenTugasActivity, idTugas)
    }

    override fun onUserJenisTugasClicked(data: JenisTugas) {
        if (data.titleJenisTugas != "Semua") {
            DosenTugasFilterActivity.start(this@DosenTugasActivity, idKelas, data.titleJenisTugas)
        }
    }
}