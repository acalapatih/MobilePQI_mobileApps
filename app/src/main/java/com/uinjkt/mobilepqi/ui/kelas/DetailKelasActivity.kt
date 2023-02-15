package com.uinjkt.mobilepqi.ui.kelas

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.addCallback
import androidx.recyclerview.widget.LinearLayoutManager
import com.uinjkt.mobilepqi.common.BaseActivity
import com.uinjkt.mobilepqi.data.DataDosen
import com.uinjkt.mobilepqi.data.DataMahasiswa
import com.uinjkt.mobilepqi.data.DataSourceKelasDosenMahasiswa
import com.uinjkt.mobilepqi.databinding.ActivityDetailKelasBinding
import com.uinjkt.mobilepqi.ui.kelas.adapter.DosenAdapter
import com.uinjkt.mobilepqi.ui.kelas.adapter.MahasiswaAdapter

class DetailKelasActivity: BaseActivity<ActivityDetailKelasBinding>() {
    private lateinit var listDosen: MutableList<DataDosen>
    private lateinit var dosenAdapter: DosenAdapter

    private lateinit var listMahasiswa: MutableList<DataMahasiswa>
    private lateinit var mahasiswaAdapter: MahasiswaAdapter
    companion object {
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, DetailKelasActivity::class.java)
            context.startActivity(starter)
        }
    }

    override fun getViewBinding(): ActivityDetailKelasBinding =
        ActivityDetailKelasBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        val backIcon = binding.icBackWhite
        backIcon.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
            onBackPressedDispatcher.addCallback(this) {
                finish()
            }
        }

        val tambahDosenText = binding.tvLabelTambahDosen
        tambahDosenText.setOnClickListener {
            TambahDosenActivity.start(this)
        }

        listDosen = DataSourceKelasDosenMahasiswa().dataDosen()

        val dosenAdapter =
            DosenAdapter(this, listDosen)
        binding.rvProfilDosen.layoutManager = LinearLayoutManager(this)
        binding.rvProfilDosen.adapter = dosenAdapter

        listMahasiswa = DataSourceKelasDosenMahasiswa().dataMahasiswa()

        val mahasiswaAdapter =
            MahasiswaAdapter(this, listMahasiswa)
        binding.rvProfilMahasiswa.layoutManager = LinearLayoutManager(this)
        binding.rvProfilMahasiswa.adapter = mahasiswaAdapter
    }
}