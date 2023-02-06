package com.uinjkt.mobilepqi.ui.setting

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatButton
import com.uinjkt.mobilepqi.R
import com.uinjkt.mobilepqi.ui.profile.ProfileActivity

class ProfilePengaturan: Activity() {
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profil_pengaturan)
        val next = findViewById<AppCompatButton>(R.id.btn_bahasa)
        next.setOnClickListener { view ->
            val bahasaIntent = Intent(view.context, ProfilePengaturanBahasa::class.java)
            startActivityForResult(bahasaIntent, 0)
        }

        val back = findViewById<ImageView>(R.id.img_back)
        back.setOnClickListener {view->
            val backIntent = Intent(view.context, ProfileActivity::class.java)
            startActivityForResult(backIntent, 0)
        }
    }
}