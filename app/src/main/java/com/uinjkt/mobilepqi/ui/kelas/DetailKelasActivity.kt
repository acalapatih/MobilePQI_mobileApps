package com.uinjkt.mobilepqi.ui.kelas

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.uinjkt.mobilepqi.common.BaseActivity
import com.uinjkt.mobilepqi.databinding.ActivityDetailKelasBinding
import com.uinjkt.mobilepqi.ui.tambahdosen.TambahDosenActivity

class DetailKelasActivity: BaseActivity<ActivityDetailKelasBinding>() {
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
        backIcon.setOnClickListener {view ->
            val backIntent = Intent(view.context, DaftarKelasActivity::class.java)
            startActivityForResult(backIntent, 0)
        }

        val tambahDosenIcon = binding.icTambahDosen
        tambahDosenIcon.setOnClickListener {view ->
            val tambahDosenIntent = Intent(view.context, TambahDosenActivity::class.java)
            startActivityForResult(tambahDosenIntent, 0)
        }

        val tambahDosenText = binding.tvLabelTambahDosen
        tambahDosenText.setOnClickListener {view ->
            val tambahDosenIntent = Intent(view.context, TambahDosenActivity::class.java)
            startActivityForResult(tambahDosenIntent, 0)
        }
    }
}