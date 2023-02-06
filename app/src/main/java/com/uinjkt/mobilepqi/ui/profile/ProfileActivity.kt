package com.uinjkt.mobilepqi.ui.profile

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.uinjkt.mobilepqi.R
import com.uinjkt.mobilepqi.ui.setting.ProfilePengaturan

class ProfileActivity : Activity() {
    /** Called when the activity is first created.  */
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profil)
        val profil = findViewById<View>(R.id.img_profil)
        profil.setOnClickListener { view ->
            val profilIntent = Intent(view.context, ProfileInfoActivity::class.java)
            startActivityForResult(profilIntent, 0)
        }

        val pengaturan = findViewById<ImageView>(R.id.img_pengaturan)
        pengaturan.setOnClickListener { view ->
            val pengaturanIntent = Intent(view.context, ProfilePengaturan::class.java)
            startActivityForResult(pengaturanIntent, 0)
        }
    }
}