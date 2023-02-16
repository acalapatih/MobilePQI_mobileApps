package com.uinjkt.mobilepqi.ui.pengaturan

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.addCallback
import com.uinjkt.mobilepqi.common.BaseActivity
import com.uinjkt.mobilepqi.databinding.ActivityProfilPengaturanBinding

class ProfilePengaturanActivity: BaseActivity<ActivityProfilPengaturanBinding>() {
    companion object {
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, ProfilePengaturanActivity::class.java)
            context.startActivity(starter)
        }
    }
    override fun getViewBinding(): ActivityProfilPengaturanBinding = ActivityProfilPengaturanBinding.inflate(layoutInflater)

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding.btnBahasa.setOnClickListener {
            ProfilePengaturanBahasaActivity.start(this)
        }

        binding.icBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
        onBackPressedDispatcher.addCallback(this) {
            finish()
        }
    }
}