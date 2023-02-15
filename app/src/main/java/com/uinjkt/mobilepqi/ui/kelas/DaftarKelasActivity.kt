package com.uinjkt.mobilepqi.ui.kelas

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.uinjkt.mobilepqi.common.BaseActivity
import com.uinjkt.mobilepqi.data.DataKelas
import com.uinjkt.mobilepqi.data.DataSourceKelasDosenMahasiswa
import com.uinjkt.mobilepqi.databinding.ActivityDaftarKelasBinding
import com.uinjkt.mobilepqi.ui.kelas.adapter.DaftarKelasAdapter

class DaftarKelasActivity: BaseActivity<ActivityDaftarKelasBinding>() {
    companion object{
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, DaftarKelasActivity::class.java)
            context.startActivity(starter)
        }
    }

    override fun getViewBinding(): ActivityDaftarKelasBinding = ActivityDaftarKelasBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        val buatkelas = binding.tvBuatkelas
        buatkelas.setOnClickListener {
            BuatKelasActivity.start(this)
        }

        val detailKelasIcon = binding.recycleViewKelas
        detailKelasIcon.setOnClickListener {
            start(this)
        }

        val listKelas = DataSourceKelasDosenMahasiswa().dataKelas()

        val daftarKelasAdapter =
            DaftarKelasAdapter(this, listKelas as MutableList<DataKelas>, listener = null)
        binding.recycleViewKelas.layoutManager = LinearLayoutManager(this)
        binding.recycleViewKelas.adapter = daftarKelasAdapter
    }
}
