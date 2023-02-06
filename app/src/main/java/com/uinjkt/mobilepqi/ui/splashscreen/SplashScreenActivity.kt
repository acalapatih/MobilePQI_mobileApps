package com.uinjkt.mobilepqi.ui.splashscreen

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.uinjkt.mobilepqi.R
import com.uinjkt.mobilepqi.ui.signin.SigninActivity

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        supportActionBar?.hide()

        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }

        val btn_mulai: Button = findViewById(R.id.btn_mulai)
        btn_mulai.setOnClickListener {
            val intent = Intent(this, SigninActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}