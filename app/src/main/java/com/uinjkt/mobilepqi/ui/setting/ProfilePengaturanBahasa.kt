package com.uinjkt.mobilepqi.ui.setting

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.uinjkt.mobilepqi.R

class ProfilePengaturanBahasa: Activity(){
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profil_pengaturan_bahasa)
        val back = findViewById<View>(R.id.img_back)
        back.setOnClickListener {view->
            val intent = Intent(view.context, ProfilePengaturan::class.java)
            startActivityForResult(intent, 0)
        }
    }
}