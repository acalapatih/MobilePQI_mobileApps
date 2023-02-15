package com.uinjkt.mobilepqi.ui.kelas

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.addCallback
import com.uinjkt.mobilepqi.common.BaseActivity
import com.uinjkt.mobilepqi.databinding.ActivityDetailKelasBinding

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
    }
}