package com.uinjkt.mobilepqi.ui.lupapassword

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.addCallback
import com.jakewharton.rxbinding2.widget.RxTextView
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

    private fun emailValidate(email: String): Boolean {
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        return email.matches(emailPattern.toRegex())
    }

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.ivCloseLupaPassword.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        onBackPressedDispatcher.addCallback(this) {
            finish()
        }

        val emailStream = RxTextView.textChanges(binding.etEmailLupaPassword)
            .skipInitialValue()
            .map { email ->
                emailValidate(email.toString()) && email.length > 5
            }
        emailStream.subscribe { isEmailValid ->
            if (!isEmailValid) {
                binding.etEmailLupaPassword.error = "Harap Masukkan Email Anda dengan benar!"
                binding.btnKirimLupaPassword.isEnabled = false
            } else {
                binding.btnKirimLupaPassword.isEnabled = true
            }
        }
    }
}