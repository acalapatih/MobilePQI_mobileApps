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

class DaftarKelasActivity : BaseActivity<ActivityDaftarKelasBinding>(),
    DaftarKelasAdapter.OnUserClickListener {
    private lateinit var listKelas: MutableList<DataKelas>

    companion object {
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, DaftarKelasActivity::class.java)
            context.startActivity(starter)
        }
    }

    override fun getViewBinding(): ActivityDaftarKelasBinding =
        ActivityDaftarKelasBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.tvBuatkelas.setOnClickListener {
            BuatKelasActivity.start(this)
        }

        listKelas = DataSourceKelasDosenMahasiswa().dataKelas()

        val daftarKelasAdapter =
            DaftarKelasAdapter(this, listKelas, this)
        binding.rvDaftarKelas.layoutManager = LinearLayoutManager(this)
        binding.rvDaftarKelas.adapter = daftarKelasAdapter


    }

    override fun onUserClicked(position: Int, clicked: String) {
        if (clicked == "anggota") {
            DetailKelasActivity.start(this)
        } else {
            BuatKelasActivity.start(this)
        }
    }
}
