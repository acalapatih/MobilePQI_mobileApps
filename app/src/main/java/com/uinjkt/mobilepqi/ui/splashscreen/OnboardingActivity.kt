package com.uinjkt.mobilepqi.ui.splashscreen

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowInsets
import android.view.WindowManager
import android.view.animation.AnimationUtils
import com.uinjkt.mobilepqi.R
import com.uinjkt.mobilepqi.common.BaseActivity
import com.uinjkt.mobilepqi.databinding.ActivityOnboardingBinding

class OnboardingActivity : BaseActivity<ActivityOnboardingBinding>() {
    companion object {
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, OnboardingActivity::class.java)
            context.startActivity(starter)
        }
    }

    override fun getViewBinding(): ActivityOnboardingBinding =
        ActivityOnboardingBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
        // set the animation
        binding.ivAwanBintang.setAnimation(trasitionAnimation)
        binding.ivLogoPujitaklim.setAnimation(trasitionAnimation)
        binding.ivLogoUinjkt.setAnimation(trasitionAnimation)
        binding.ivLogoQuran.setAnimation(trasitionAnimation)
        binding.tvMobilepqiSplash.setAnimation(trasitionAnimation)
        binding.tvDeskripsiSplash.setAnimation(trasitionAnimation)

        // launch the SplashScreenActivity with delay 1500ms
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            // start the SplashScreenActivity
            SplashScreenActivity.start(this)
            // remove this activity from the stack
            finish()
        }, 1500)
    }
}