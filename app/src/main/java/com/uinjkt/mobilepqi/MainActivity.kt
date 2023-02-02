package com.uinjkt.mobilepqi

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        setContentView(R.layout.profil)
        val informasi = findViewById<View>(R.id.img_profil) as ImageView
        informasi.setOnClickListener { view ->
            val informasiIntent = Intent(view.context, Activity2::class.java)
            startActivityForResult(informasiIntent, 0)
        }
        supportActionBar?.hide()
    }
}