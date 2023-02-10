package com.uinjkt.mobilepqi.ui.setting

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.uinjkt.mobilepqi.common.BaseActivity
import com.uinjkt.mobilepqi.databinding.ActivityProfilPengaturanBinding
import com.uinjkt.mobilepqi.ui.profile.ProfileActivity

class ProfilePengaturan: BaseActivity<ActivityProfilPengaturanBinding>() {
    companion object {
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, ProfilePengaturan::class.java)
            context.startActivity(starter)
        }
    }
    override fun getViewBinding(): ActivityProfilPengaturanBinding = ActivityProfilPengaturanBinding.inflate(layoutInflater)

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        val next = binding.btnBahasa
        next.setOnClickListener { view ->
            val bahasaIntent = Intent(view.context, ProfilePengaturanBahasa::class.java)
            startActivityForResult(bahasaIntent, 0)
        }

        val back = binding.icBack
        back.setOnClickListener {view->
            val backIntent = Intent(view.context, ProfileActivity::class.java)
            startActivityForResult(backIntent, 0)
        }
    }
}