package com.uinjkt.mobilepqi.ui.splashscreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.uinjkt.mobilepqi.R

class OnboardingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        supportActionBar?.hide()
        // launch the SplashScreenActivity with delay 1500ms
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            // start the SplashScreenActivity
            val intent = Intent(this, SplashScreenActivity::class.java)
            startActivity(intent)
            // remove this activity from the stack
            finish()
        }, 1500)
    }
}