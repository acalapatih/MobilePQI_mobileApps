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
import com.uinjkt.mobilepqi.databinding.ActivityMahasiswaTugasSemuaBinding
import com.uinjkt.mobilepqi.ui.mahasiswa.MenuMahasiswaJenisTugasAdapter
import com.uinjkt.mobilepqi.ui.mahasiswa.menuqiroah.MahasiswaMateriQiroahActivity

class MahasiswaTugasActivity : BaseActivity<ActivityMahasiswaTugasSemuaBinding>(), MenuMahasiswaJenisTugasAdapter.OnUserClickListener {

    private lateinit var listJenisTugas: MutableList<DataJenisTugas>
    private lateinit var mahasiswaJenisTugasAdapter: MenuMahasiswaJenisTugasAdapter

    companion object {
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, MahasiswaTugasActivity::class.java)
            context.startActivity(starter)
        }

        private const val SEMUA = 1
//        private const val PRAKTIKUM_IBADAH = 2
//        private const val PRAKTIKUM_QIROAH = 3
//        private const val HAFALAN_SURAH = 4
//        private const val HAFALAN_DOA = 5
    }
    override fun getViewBinding(): ActivityMahasiswaTugasSemuaBinding = ActivityMahasiswaTugasSemuaBinding.inflate(layoutInflater)

    override fun onUserClicked(position: Int) {
            MahasiswaMateriQiroahActivity.start(this@MahasiswaTugasActivity)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize data.
        listJenisTugas = DataSourceJenisTugas().setDataJenisTugasClicked(SEMUA)

        // Initialize Adapter
        mahasiswaJenisTugasAdapter = MenuMahasiswaJenisTugasAdapter(this, listJenisTugas, this)
        binding.rvJenisTugasMahasiswaSemua.adapter = mahasiswaJenisTugasAdapter

        // Set Title Action Bar
        binding.tvTitleAppBarMahasiswa.text = getString(R.string.tv_titleappbar_tugas)

        // Set Layout Manager
        binding.rvJenisTugasMahasiswaSemua.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false)

        // icon Close onClickListener
        binding.ivIconClose.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        onBackPressedDispatcher.addCallback(this) {
            finish()
        }
    }



}