package com.uinjkt.mobilepqi.ui.splashscreen

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.uinjkt.mobilepqi.common.BaseActivity
import com.uinjkt.mobilepqi.databinding.ActivityOnboardingBinding
import com.uinjkt.mobilepqi.ui.signin.SigninActivity

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

        binding.btnMulai.setOnClickListener {
            SigninActivity.start(this)
            finish()
        }
    }
}