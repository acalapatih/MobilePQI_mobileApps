package com.uinjkt.mobilepqi.ui.mahasiswa.menutugas

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
import com.uinjkt.mobilepqi.databinding.ActivityMahasiswaTugasSemuaBinding
import com.uinjkt.mobilepqi.ui.mahasiswa.ListMahasiswaTugasAdapterList
import com.uinjkt.mobilepqi.ui.mahasiswa.MenuMahasiswaJenisTugasAdapterNew
import org.koin.androidx.viewmodel.ext.android.viewModel

class MahasiswaTugasActivity : BaseActivity<ActivityMahasiswaTugasSemuaBinding>(),
    MenuMahasiswaJenisTugasAdapterNew.OnUserClickJenisTugasListener,
    ListMahasiswaTugasAdapterList.OnUserClickTugasListener {

    companion object {
        @JvmStatic
        fun start(context: Context, idKelas: Int) {
            val starter = Intent(context, MahasiswaTugasActivity::class.java)
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
    private lateinit var mahasiswaJenisTugasAdapter: MenuMahasiswaJenisTugasAdapterNew
    private lateinit var mahasiswaTugasQiroahAdapter: ListMahasiswaTugasAdapterList
    private lateinit var mahasiswaTugasIbadahAdapter: ListMahasiswaTugasAdapterList
    private lateinit var mahasiswaTugasSurahAdapter: ListMahasiswaTugasAdapterList
    private lateinit var mahasiswaTugasDoaAdapter: ListMahasiswaTugasAdapterList

    private val viewModel by viewModel<MahasiswaTugasViewModel>()
    private val idKelas by lazy { intent.getIntExtra(ID_KELAS, 0) }

    override fun getViewBinding(): ActivityMahasiswaTugasSemuaBinding =
        ActivityMahasiswaTugasSemuaBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initListener()
        initObserver()
    }

    private fun initView() {
        // Set Title Action Bar
        binding.tvTitleAppBarMahasiswaSemua.text = getString(R.string.tv_titleappbar_tugas)
        getListTugas()
    }

    private fun getListTugas() {
        viewModel.getListTugas(idKelas)
    }

    private fun initListener() {
        binding.ivIconClose.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        onBackPressedDispatcher.addCallback(this) {
            finish()
        }
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
    }

    private fun actionAfterGetListTugas(model: GetListTugasModel) {
        initAdapter(model)
        showEmptyStateEachTopic(model)
    }

    private fun showEmptyStateEachTopic(model: GetListTugasModel) {
        binding.tvBelumAdaTugasIbadah.isVisible = model.ibadah.isEmpty()
        binding.tvBelumAdaTugasQiroah.isVisible = model.qiroah.isEmpty()
        binding.tvBelumAdaTugasHafalanDoa.isVisible = model.doa.isEmpty()
        binding.tvBelumAdaTugasHafalanSurah.isVisible = model.surah.isEmpty()
    }

    private fun initAdapter(model: GetListTugasModel) {
        // Initialize Adapter Jenis Tugas
        mahasiswaJenisTugasAdapter = MenuMahasiswaJenisTugasAdapterNew(this, listJenisTugas, this)
        binding.rvJenisTugasMahasiswaSemua.adapter = mahasiswaJenisTugasAdapter

        // Initialize Adapter Tugas Qiroah
        mahasiswaTugasQiroahAdapter = ListMahasiswaTugasAdapterList(this, model.qiroah, this)
        binding.rvListTugasMahasiswaQiroah.adapter = mahasiswaTugasQiroahAdapter

        // Initialize Adapter Tugas Ibadah
        mahasiswaTugasIbadahAdapter = ListMahasiswaTugasAdapterList(this, model.ibadah, this)
        binding.rvListTugasMahasiswaIbadah.adapter = mahasiswaTugasIbadahAdapter

        // Initialize Adapter Tugas Hafalan Surah
        mahasiswaTugasSurahAdapter = ListMahasiswaTugasAdapterList(this, model.surah, this)
        binding.rvListTugasMahasiswaHafalanSurah.adapter = mahasiswaTugasSurahAdapter

        // Initialize Adapter Tugas Hafalan Doa
        mahasiswaTugasDoaAdapter = ListMahasiswaTugasAdapterList(this, model.doa, this)
        binding.rvListTugasMahasiswaHafalanDoa.adapter = mahasiswaTugasDoaAdapter

        // Set Layout Manager
        binding.rvJenisTugasMahasiswaSemua.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvListTugasMahasiswaQiroah.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvListTugasMahasiswaIbadah.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvListTugasMahasiswaHafalanSurah.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvListTugasMahasiswaHafalanDoa.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    private fun showLoading(value: Boolean) {
        binding.pbLoadingScreen.isVisible = value
        binding.nsvContentDetail.isVisible = !value
    }


    override fun onUserTugasClicked(idTugas: Int) {
        MahasiswaDetailTugasActivity.start(this@MahasiswaTugasActivity, idTugas)
    }

    override fun onUserJenisTugasClicked(data: JenisTugas) {
        MahasiswaTugasFilterActivity.start(
            this@MahasiswaTugasActivity,
            idKelas,
            data.titleJenisTugas
        )
    }

    override fun onRestart() {
        super.onRestart()
        mahasiswaJenisTugasAdapter.changeSelected(0)
        getListTugas()
    }
}