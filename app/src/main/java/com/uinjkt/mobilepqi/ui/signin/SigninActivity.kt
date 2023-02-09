package com.uinjkt.mobilepqi.ui.signin

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import com.jakewharton.rxbinding2.widget.RxTextView
import com.uinjkt.mobilepqi.R
import com.uinjkt.mobilepqi.common.BaseActivity
import com.uinjkt.mobilepqi.databinding.ActivitySigninBinding
import com.uinjkt.mobilepqi.ui.lupapassword.LupaPasswordActivity
import com.uinjkt.mobilepqi.ui.signup.SignupActivity
import io.reactivex.Observable


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

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // show hide password with eye icon
        var isSelected = true
        binding.ivShowHidePassword.setOnClickListener {
            if (isSelected) {
                binding.ivShowHidePassword.setImageResource(R.drawable.ic_eye_hide_password)
                val start = binding.etPasswordSignin.selectionStart
                val end = binding.etPasswordSignin.selectionEnd
                binding.etPasswordSignin.transformationMethod = null
                binding.etPasswordSignin.setSelection(start, end)
                isSelected = false
            } else {
                binding.ivShowHidePassword.setImageResource(R.drawable.ic_eye_show_password)
                val start = binding.etPasswordSignin.selectionStart
                val end = binding.etPasswordSignin.selectionEnd
                binding.etPasswordSignin.transformationMethod = PasswordTransformationMethod()
                binding.etPasswordSignin.setSelection(start, end)
                isSelected = true
            }
        }

        binding.tvBelumDaftarClick.setOnClickListener {
            SignupActivity.start(this)
            finish()
        }

        binding.tvLupaPassword.setOnClickListener {
            LupaPasswordActivity.start(this)
        }

        val nimNipStream = RxTextView.textChanges(binding.etNipNimSignin)
            .skipInitialValue()
            .map { nim ->
                nim.length > 6 //isi sama ketentuan
            }
        nimNipStream.subscribe { isValid ->
            if (isValid) {
                //Action kalo nip or nim sesuai
            } else {
                //Action kalo nip or nim ga sesuai
            }
        }

        val passwordStream = RxTextView.textChanges(binding.etPasswordSignin)
            .skipInitialValue()
            .map { password ->
                password.length > 6 //Ganti sama regex disini
            }
        passwordStream.subscribe { isValid ->
            if (isValid) {
                //Action kalo password sesuai
            } else {
                //Action kalo password ga sesuai
            }
        }

        Observable.combineLatest(
            nimNipStream,
            passwordStream
        ) { nimNipValid: Boolean, passwordValid: Boolean ->
            nimNipValid && passwordValid
        }.subscribe { isValid ->
            binding.btnSignin.isEnabled = isValid //enable disable button dari sini
        }
    }
}