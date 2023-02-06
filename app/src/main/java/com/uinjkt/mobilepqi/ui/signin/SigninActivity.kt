package com.uinjkt.mobilepqi.ui.signin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.uinjkt.mobilepqi.R

class SigninActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()
    }
}