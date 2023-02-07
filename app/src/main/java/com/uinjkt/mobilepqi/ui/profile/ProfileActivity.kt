package com.uinjkt.mobilepqi.ui.profile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.uinjkt.mobilepqi.common.BaseActivity
import com.uinjkt.mobilepqi.databinding.ActivityProfilBinding
import com.uinjkt.mobilepqi.ui.dashboard.DashboardActivity
import com.uinjkt.mobilepqi.ui.setting.ProfilePengaturan

class ProfileActivity : BaseActivity<ActivityProfilBinding>() {
    /** Called when the activity is first created.  */
    companion object {
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, ProfileActivity::class.java)
            context.startActivity(starter)
        }
    }

    override fun getViewBinding(): ActivityProfilBinding = ActivityProfilBinding.inflate(layoutInflater)

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        val close = binding.imgClose
        close.setOnClickListener {view ->
            val closeIntent = Intent(view.context, DashboardActivity::class.java)
            startActivityForResult(closeIntent, 0)
        }

        val profil = binding.imgProfil
        profil.setOnClickListener { view ->
            val profilIntent = Intent(view.context, ProfileInfoActivity::class.java)
            startActivityForResult(profilIntent, 0)
        }

        val pengaturan = binding.imgPengaturan
        pengaturan.setOnClickListener { view ->
            val pengaturanIntent = Intent(view.context, ProfilePengaturan::class.java)
            startActivityForResult(pengaturanIntent, 0)
        }
    }
}