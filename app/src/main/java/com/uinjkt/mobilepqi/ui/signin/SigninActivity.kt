package com.uinjkt.mobilepqi.ui.signin

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import com.uinjkt.mobilepqi.R
import com.uinjkt.mobilepqi.common.BaseActivity
import com.uinjkt.mobilepqi.databinding.ActivitySigninBinding
import com.uinjkt.mobilepqi.ui.signup.SignupActivity


class SigninActivity : BaseActivity<ActivitySigninBinding>() {
    companion object {
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, SigninActivity::class.java)
            context.startActivity(starter)
        }
    }

    override fun getViewBinding(): ActivitySigninBinding =
        ActivitySigninBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()

        // show hide password with eye icon
        var isSelected = true
        binding.ivShowHidePassword.setOnClickListener {
            if (isSelected) {
                binding.ivShowHidePassword.setImageResource(R.drawable.ic_eye_hide_password)
                val start = binding.txtPasswordSignin.getSelectionStart()
                val end = binding.txtPasswordSignin.getSelectionEnd()
                binding.txtPasswordSignin.setTransformationMethod(null)
                binding.txtPasswordSignin.setSelection(start, end)
                isSelected = false;
            } else {
                binding.ivShowHidePassword.setImageResource(R.drawable.ic_eye_show_password)
                val start = binding.txtPasswordSignin.getSelectionStart()
                val end = binding.txtPasswordSignin.getSelectionEnd()
                binding.txtPasswordSignin.transformationMethod = PasswordTransformationMethod()
                binding.txtPasswordSignin.setSelection(start, end)
                isSelected = true;
            }
        }

        binding.tvBelumDaftarClick.setOnClickListener {
            SignupActivity.start(this)
        }
    }
}