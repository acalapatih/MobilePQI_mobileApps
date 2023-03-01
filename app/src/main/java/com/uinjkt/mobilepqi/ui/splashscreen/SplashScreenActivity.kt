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
import com.uinjkt.mobilepqi.ui.dashboard.activity.DashboardActivity
import com.uinjkt.mobilepqi.ui.kelas.DaftarKelasActivity
import com.uinjkt.mobilepqi.ui.signin.SigninActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : BaseActivity<ActivitySplashScreenBinding>() {

    private val viewModel by viewModel<SplashOnboardingViewModel>()

    override fun getViewBinding(): ActivitySplashScreenBinding =
        ActivitySplashScreenBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.getShowOnboardingStatus()
        viewModel.getToken()
        viewModel.getUserRole()

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
        val transitionAnimation =
            AnimationUtils.loadAnimation(this, R.anim.splash_screen_transition)
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
            redirectPage()
        }, 1500)
    }

    private fun redirectPage() {
        if (viewModel.showOnboardingStatus.value == true) {
            OnboardingActivity.start(this)
            finish()
        } else {
            if (viewModel.token.value?.isEmpty() == true) {
                SigninActivity.start(this)
                finish()
            } else {
                if (viewModel.userRole.value?.equals("mahasiswa") == true) {
                    DashboardActivity.start(this, "")
                    finish()
                } else {
                    DaftarKelasActivity.start(this)
                    finish()
                }
            }
        }
    }
}