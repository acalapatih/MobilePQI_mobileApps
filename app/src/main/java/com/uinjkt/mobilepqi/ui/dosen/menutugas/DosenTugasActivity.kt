package com.uinjkt.mobilepqi.ui.dosen.menutugas

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.addCallback
import androidx.recyclerview.widget.LinearLayoutManager
import com.uinjkt.mobilepqi.R
import com.uinjkt.mobilepqi.common.BaseActivity
import com.uinjkt.mobilepqi.data.DataJenisTugas
import com.uinjkt.mobilepqi.data.DataSourceJenisTugas
import com.uinjkt.mobilepqi.data.DataSourceTugas
import com.uinjkt.mobilepqi.data.DataTugas
import com.uinjkt.mobilepqi.databinding.ActivityDosenTugasSemuaBinding
import com.uinjkt.mobilepqi.ui.mahasiswa.ListMahasiswaTugasAdapter
import com.uinjkt.mobilepqi.ui.mahasiswa.MenuMahasiswaJenisTugasAdapter

class DosenTugasActivity : BaseActivity<ActivityDosenTugasSemuaBinding>(), MenuMahasiswaJenisTugasAdapter.OnUserClickJenisTugasListener, ListMahasiswaTugasAdapter.OnUserClickTugasListener {

    private lateinit var listJenisTugas: MutableList<DataJenisTugas>
    private lateinit var listTugas: MutableList<DataTugas>
    private lateinit var dosenJenisTugasAdapter: MenuMahasiswaJenisTugasAdapter
    private lateinit var dosenTugasQiroahAdapter: ListMahasiswaTugasAdapter
    private lateinit var dosenTugasIbadahAdapter: ListMahasiswaTugasAdapter
    private lateinit var dosenTugasSurahAdapter: ListMahasiswaTugasAdapter
    private lateinit var dosenTugasDoaAdapter: ListMahasiswaTugasAdapter

    companion object {
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, DosenTugasActivity::class.java)
            context.startActivity(starter)
        }
        private const val SEMUA = 1
    }

    override fun getViewBinding(): ActivityDosenTugasSemuaBinding = ActivityDosenTugasSemuaBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize data.
        listJenisTugas = DataSourceJenisTugas().setDataJenisTugasClicked(SEMUA)
        listTugas = DataSourceTugas().dataTugas

        // Initialize Adapter Jenis Tugas
        dosenJenisTugasAdapter = MenuMahasiswaJenisTugasAdapter(this, listJenisTugas, this)
        binding.rvJenisTugasDosenSemua.adapter = dosenJenisTugasAdapter

        // Initialize Adapter Tugas Qiroah
        dosenTugasQiroahAdapter = ListMahasiswaTugasAdapter(this, listTugas, this)
        binding.rvListTugasDosenQiroah.adapter = dosenTugasQiroahAdapter

        // Initialize Adapter Tugas Ibadah
        dosenTugasIbadahAdapter = ListMahasiswaTugasAdapter(this, listTugas, this)
        binding.rvListTugasDosenIbadah.adapter = dosenTugasIbadahAdapter

        // Initialize Adapter Tugas Hafalan Surah
        dosenTugasSurahAdapter = ListMahasiswaTugasAdapter(this, listTugas, this)
        binding.rvListTugasDosenHafalanSurah.adapter = dosenTugasSurahAdapter

        // Initialize Adapter Tugas Hafalan Doa
        dosenTugasDoaAdapter = ListMahasiswaTugasAdapter(this, listTugas, this)
        binding.rvListTugasDosenHafalanDoa.adapter = dosenTugasDoaAdapter

        // Set Title Action Bar
        binding.tvTitleAppBarDosenSemua.text = getString(R.string.tv_titleappbar_tugas)

        // Set Layout Manager
        binding.rvJenisTugasDosenSemua.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvListTugasDosenQiroah.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvListTugasDosenIbadah.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvListTugasDosenHafalanSurah.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvListTugasDosenHafalanDoa.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        // icon Close onClickListener
        binding.ivIconClose.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }


        binding.tvIconAddTugas.setOnClickListener {
            DosenBuatEditTugasActivity.start(this)
        }

        binding.tvIconUnduhNilaiTugas.setOnClickListener {
            showTwoActionDialog("Unduh Nilai?", btnPositiveMessage = "Unduh", btnNegativeMessage = "batal") {
                showOneActionDialog("Nilai Berhasil Diunduh", "Okay")
            }
        }

        onBackPressedDispatcher.addCallback(this) {
            finish()
        }

    }

    override fun onUserTugasClicked(position: Int) {
        DosenDetailTugasActivity.start(this@DosenTugasActivity)
    }

    override fun onUserJenisTugasClicked(data: DataJenisTugas) {
        DosenTugasFilterActivity.start(this@DosenTugasActivity, data.idJenisTugas)
    }
}