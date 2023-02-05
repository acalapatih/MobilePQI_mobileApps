package com.uinjkt.mobilepqi.ui.splashscreen

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowInsets
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.uinjkt.mobilepqi.R

class OnboardingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

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

        // animation for the splash screen
        val trasitionAnimation = AnimationUtils.loadAnimation(this, R.anim.splash_screen_transition)
        // get the id of the view
        val iv_awan_bintang: ImageView = findViewById(R.id.iv_awan_bintang)
        val iv_logo_pujitaklim: ImageView = findViewById(R.id.iv_logo_pujitaklim)
        val iv_logo_uinjkt: ImageView = findViewById(R.id.iv_logo_uinjkt)
        val iv_logo_quran: ImageView = findViewById(R.id.iv_logo_quran)
        val tv_mobilepqi_splash: TextView = findViewById(R.id.tv_mobilepqi_splash)
        val tv_deskripsi_splash: TextView = findViewById(R.id.tv_deskripsi_splash)
        // set the animation
        iv_awan_bintang.setAnimation(trasitionAnimation)
        iv_logo_pujitaklim.setAnimation(trasitionAnimation)
        iv_logo_uinjkt.setAnimation(trasitionAnimation)
        iv_logo_quran.setAnimation(trasitionAnimation)
        tv_mobilepqi_splash.setAnimation(trasitionAnimation)
        tv_deskripsi_splash.setAnimation(trasitionAnimation)

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