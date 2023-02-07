package com.uinjkt.mobilepqi.ui.setting

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.uinjkt.mobilepqi.common.BaseActivity
import com.uinjkt.mobilepqi.databinding.ActivityProfilPengaturanBahasaBinding

class ProfilePengaturanBahasa : BaseActivity<ActivityProfilPengaturanBahasaBinding>() {
    companion object {
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, ProfilePengaturanBahasa::class.java)
            context.startActivity(starter)
        }
    }

    override fun getViewBinding(): ActivityProfilPengaturanBahasaBinding =
        ActivityProfilPengaturanBahasaBinding.inflate(layoutInflater)

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        val back = binding.imgBack
        back.setOnClickListener { view ->
            val intent = Intent(view.context, ProfilePengaturan::class.java)
            startActivityForResult(intent, 0)
        }
    }
}