package com.uinjkt.mobilepqi

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView

class Activity1 : Activity() {
    /** Called when the activity is first created.  */
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profil)
        val next = findViewById<View>(R.id.img_profil) as ImageView
        next.setOnClickListener { view ->
            val myIntent = Intent(view.context, Activity2::class.java)
            startActivityForResult(myIntent, 0)
        }
    }
}