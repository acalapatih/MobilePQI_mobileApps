package com.uinjkt.mobilepqi.ui.profile

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.uinjkt.mobilepqi.R

class ProfileInfoActivity : Activity() {
    /**
     * Called when the activity is first created.
     */
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profil_informasi)
        val back = findViewById<ImageView>(R.id.img_back)
        back.setOnClickListener {view->
            val backIntent = Intent(view.context, ProfileActivity::class.java)
            startActivityForResult(backIntent, 0)
        }
    }
}