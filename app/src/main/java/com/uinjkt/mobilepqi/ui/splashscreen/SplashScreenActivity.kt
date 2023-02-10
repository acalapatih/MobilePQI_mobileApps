package com.uinjkt.mobilepqi.ui.splashscreen

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowInsets
import android.view.WindowManager
import android.view.animation.AnimationUtils
import com.uinjkt.mobilepqi.R
import com.uinjkt.mobilepqi.common.BaseActivity
import com.uinjkt.mobilepqi.databinding.ActivitySplashScreenBinding

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : BaseActivity<ActivitySplashScreenBinding>() {

    override fun getViewBinding(): ActivitySplashScreenBinding =
        ActivitySplashScreenBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
        val transitionAnimation = AnimationUtils.loadAnimation(this, R.anim.splash_screen_transition)
        // set the animation
        binding.ivAwanBintang.animation = transitionAnimation
        binding.ivLogoPujitaklim.animation = transitionAnimation
        binding.ivLogoUinjkt.animation = transitionAnimation
        binding.ivLogoQuran.animation = transitionAnimation
        binding.tvMobilepqiSplash.animation = transitionAnimation
        binding.tvDeskripsiSplash.animation = transitionAnimation

        // launch the OnboardingActivity with delay 1500ms
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            // start the OnboardingActivity
            OnboardingActivity.start(this)
            // remove this activity from the stack
            finish()
        }, 1500)
    }
}