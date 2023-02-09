package com.uinjkt.mobilepqi.ui.kelas

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.uinjkt.mobilepqi.common.BaseActivity
import com.uinjkt.mobilepqi.databinding.ActivityDaftarKelasBinding

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
        val buatkelas = binding.icBuatkelas
        buatkelas.setOnClickListener {view ->
            val buatkelasIntent = Intent(view.context, BuatKelasActivity::class.java)
            startActivityForResult(buatkelasIntent, 0)
        }
        val buatkelas2 = binding.tvBuatkelas
        buatkelas2.setOnClickListener {view ->
            val buatkelasIntent = Intent(view.context, BuatKelasActivity::class.java)
            startActivityForResult(buatkelasIntent, 0)
        }

        val detailKelasIcon = binding.icDetailKelas
        detailKelasIcon.setOnClickListener{view ->
            val detailKelasIntent = Intent(view.context, DetailKelasActivity::class.java)
            startActivityForResult(detailKelasIntent, 0)
        }
    }
}