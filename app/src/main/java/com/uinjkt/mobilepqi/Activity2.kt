package com.uinjkt.mobilepqi

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView

class Activity2 : Activity() {
    /**
     * Called when the activity is first created.
     */
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profil_informasi)
        val back = findViewById<View>(R.id.img_back) as ImageView
        back.setOnClickListener {
            val intent = Intent()
            setResult(RESULT_OK, intent)
            finish()
        }

        val imgFakultas = findViewById<ImageButton>(R.id.img_edit_fakultas)
        val editFakultas = findViewById<EditText>(R.id.input_fakultas)

        imgFakultas.setOnClickListener()
    }
}