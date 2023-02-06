package com.uinjkt.mobilepqi.ui.dashboard

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.uinjkt.mobilepqi.R
import com.uinjkt.mobilepqi.ui.profile.ProfileActivity
import com.uinjkt.mobilepqi.ui.profile.ProfileInfoActivity

class Dashboard: Activity() {
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dashboard)
        val user = findViewById<View>(R.id.img_user)
        user.setOnClickListener { view ->
            val userIntent = Intent(view.context, ProfileActivity::class.java)
            startActivityForResult(userIntent, 0)
        }
    }
}