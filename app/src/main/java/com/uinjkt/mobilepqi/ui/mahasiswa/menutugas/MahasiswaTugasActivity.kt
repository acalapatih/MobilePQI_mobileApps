package com.uinjkt.mobilepqi.ui.mahasiswa.menutugas

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
import com.uinjkt.mobilepqi.databinding.ActivityMahasiswaTugasSemuaBinding
import com.uinjkt.mobilepqi.ui.mahasiswa.ListMahasiswaTugasAdapter
import com.uinjkt.mobilepqi.ui.mahasiswa.MenuMahasiswaJenisTugasAdapter

class MahasiswaTugasActivity : BaseActivity<ActivityMahasiswaTugasSemuaBinding>(),
    MenuMahasiswaJenisTugasAdapter.OnUserClickJenisTugasListener, ListMahasiswaTugasAdapter.OnUserClickTugasListener {

    private lateinit var listJenisTugas: MutableList<DataJenisTugas>
    private lateinit var listTugas: MutableList<DataTugas>
    private lateinit var mahasiswaJenisTugasAdapter: MenuMahasiswaJenisTugasAdapter
    private lateinit var mahasiswaTugasQiroahAdapter: ListMahasiswaTugasAdapter
    private lateinit var mahasiswaTugasIbadahAdapter: ListMahasiswaTugasAdapter
    private lateinit var mahasiswaTugasSurahAdapter: ListMahasiswaTugasAdapter
    private lateinit var mahasiswaTugasDoaAdapter: ListMahasiswaTugasAdapter

    companion object {
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, MahasiswaTugasActivity::class.java)
            context.startActivity(starter)
        }

        private const val SEMUA = 1

    }

    override fun getViewBinding(): ActivityMahasiswaTugasSemuaBinding =
        ActivityMahasiswaTugasSemuaBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize data.
        listJenisTugas = DataSourceJenisTugas().setDataJenisTugasClicked(SEMUA)
        listTugas = DataSourceTugas().dataTugas

        // Initialize Adapter Jenis Tugas
        mahasiswaJenisTugasAdapter = MenuMahasiswaJenisTugasAdapter(this, listJenisTugas, this)
        binding.rvJenisTugasMahasiswaSemua.adapter = mahasiswaJenisTugasAdapter

        // Initialize Adapter Tugas Qiroah
        mahasiswaTugasQiroahAdapter = ListMahasiswaTugasAdapter(this, listTugas, this)
        binding.rvListTugasMahasiswaQiroah.adapter = mahasiswaTugasQiroahAdapter

        // Initialize Adapter Tugas Ibadah
        mahasiswaTugasIbadahAdapter = ListMahasiswaTugasAdapter(this, listTugas, this)
        binding.rvListTugasMahasiswaIbadah.adapter = mahasiswaTugasIbadahAdapter

        // Initialize Adapter Tugas Hafalan Surah
        mahasiswaTugasSurahAdapter = ListMahasiswaTugasAdapter(this, listTugas, this)
        binding.rvListTugasMahasiswaHafalanSurah.adapter = mahasiswaTugasSurahAdapter

        // Initialize Adapter Tugas Hafalan Doa
        mahasiswaTugasDoaAdapter = ListMahasiswaTugasAdapter(this, listTugas, this)
        binding.rvListTugasMahasiswaHafalanDoa.adapter = mahasiswaTugasDoaAdapter

        // Set Title Action Bar
        binding.tvTitleAppBarMahasiswaSemua.text = getString(R.string.tv_titleappbar_tugas)

        // Set Layout Manager
        binding.rvJenisTugasMahasiswaSemua.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvListTugasMahasiswaQiroah.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvListTugasMahasiswaIbadah.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvListTugasMahasiswaHafalanSurah.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvListTugasMahasiswaHafalanDoa.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        // icon Close onClickListener
        binding.ivIconClose.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        onBackPressedDispatcher.addCallback(this) {
            finish()
        }
    }


    override fun onUserTugasClicked(position: Int) {
        MahasiswaDetailTugasActivity.start(this@MahasiswaTugasActivity)
    }

    override fun onUserJenisTugasClicked(data: DataJenisTugas) {
        MahasiswaTugasFilterActivity.start(this@MahasiswaTugasActivity, data.idJenisTugas)
    }


}