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
import org.koin.androidx.viewmodel.ext.android.viewModel

class SigninActivity : BaseActivity<ActivitySigninBinding>() {
    companion object {
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, SigninActivity::class.java)
            context.startActivity(starter)
        }
    }

    private val viewModel by viewModel<SignInViewModel>()

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
            .map { nidn_nip_nim ->
                nidn_nip_nim.length > 5
            }
        nimNipStream.subscribe { isUserValid ->
            if (!isUserValid) {
                binding.etNipNimSignin.error = "Harap masukkan NIDN/NIP/NIM Anda dengan benar!"
            }
        }

        val passwordStream = RxTextView.textChanges(binding.etPasswordSignin)
            .skipInitialValue()
            .map { password ->
                password.length > 5
            }
        passwordStream.subscribe { isPasswordValid ->
            if (!isPasswordValid) {
                binding.etPasswordSignin.setError("Harap masukkan password Anda dengan benar!", null)
            }
        }

        Observable.combineLatest(
            nimNipStream,
            passwordStream
        ) { nimNipValid: Boolean, passwordValid: Boolean ->
            nimNipValid && passwordValid
        }.subscribe { isButtonValid ->
            binding.btnSignin.isEnabled = isButtonValid
        }
    }
}