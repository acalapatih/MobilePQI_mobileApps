package com.uinjkt.mobilepqi.ui.signup

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import com.uinjkt.mobilepqi.R
import com.uinjkt.mobilepqi.common.BaseActivity
import com.uinjkt.mobilepqi.databinding.ActivitySignupBinding
import com.uinjkt.mobilepqi.ui.signin.SigninActivity

class SignupActivity : BaseActivity<ActivitySignupBinding>() {
    companion object {
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, SignupActivity::class.java)
            context.startActivity(starter)
        }
    }

    override fun getViewBinding(): ActivitySignupBinding =
        ActivitySignupBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()

        // show hide password with eye icon
        var isSelected = true
        binding.ivShowHidePassword.setOnClickListener {
            if (isSelected) {
                binding.ivShowHidePassword.setImageResource(R.drawable.ic_eye_hide_password)
                val start = binding.txtPasswordSignup.getSelectionStart()
                val end = binding.txtPasswordSignup.getSelectionEnd()
                binding.txtPasswordSignup.setTransformationMethod(null)
                binding.txtPasswordSignup.setSelection(start, end)
                isSelected = false;
            } else {
                binding.ivShowHidePassword.setImageResource(R.drawable.ic_eye_show_password)
                val start = binding.txtPasswordSignup.getSelectionStart()
                val end = binding.txtPasswordSignup.getSelectionEnd()
                binding.txtPasswordSignup.transformationMethod = PasswordTransformationMethod()
                binding.txtPasswordSignup.setSelection(start, end)
                isSelected = true;
            }
        }

        binding.tvSudahDaftarClick.setOnClickListener {
            SigninActivity.start(this)
        }
    }
}