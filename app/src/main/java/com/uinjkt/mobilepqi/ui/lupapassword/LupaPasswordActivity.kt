package com.uinjkt.mobilepqi.ui.lupapassword

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.uinjkt.mobilepqi.common.BaseActivity
import com.uinjkt.mobilepqi.databinding.ActivityLupaPasswordBinding
import com.uinjkt.mobilepqi.ui.signin.SigninActivity

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

        supportActionBar?.hide()

        binding.ivCloseLupaPassword.setOnClickListener {
            SigninActivity.start(this)
        }
    }
}