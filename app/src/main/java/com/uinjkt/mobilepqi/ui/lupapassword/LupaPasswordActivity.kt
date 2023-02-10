package com.uinjkt.mobilepqi.ui.lupapassword

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.addCallback
import com.uinjkt.mobilepqi.common.BaseActivity
import com.uinjkt.mobilepqi.databinding.ActivityLupaPasswordBinding

class LupaPasswordActivity : BaseActivity<ActivityLupaPasswordBinding>() {
    companion object {
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, LupaPasswordActivity::class.java)
            context.startActivity(starter)
        }
    }

    override fun getViewBinding(): ActivityLupaPasswordBinding =
        ActivityLupaPasswordBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.ivCloseLupaPassword.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        onBackPressedDispatcher.addCallback(this) {
            finish()
        }
    }
}