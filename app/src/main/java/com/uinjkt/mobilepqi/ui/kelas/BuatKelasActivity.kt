package com.uinjkt.mobilepqi.ui.kelas

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.uinjkt.mobilepqi.common.BaseActivity
import com.uinjkt.mobilepqi.databinding.ActivityBuatKelasBinding

class BuatKelasActivity: BaseActivity<ActivityBuatKelasBinding>() {
    companion object{
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, DaftarKelasActivity::class.java)
            context.startActivity(starter)
        }
    }

    override fun getViewBinding(): ActivityBuatKelasBinding = ActivityBuatKelasBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        val backIntent = binding.icClose
        backIntent.setOnClickListener {view ->
            val backIntent = Intent(view.context, DaftarKelasActivity::class.java)
            startActivityForResult(backIntent, 0)
        }
    }
}