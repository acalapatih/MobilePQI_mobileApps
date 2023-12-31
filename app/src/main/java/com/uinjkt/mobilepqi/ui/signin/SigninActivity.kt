package com.uinjkt.mobilepqi.ui.signin

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import com.jakewharton.rxbinding2.widget.RxTextView
import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.data.source.remote.response.signin.SigninPayload
import com.mobilepqi.core.domain.model.signin.SigninModel
import com.uinjkt.mobilepqi.R
import com.uinjkt.mobilepqi.common.BaseActivity
import com.uinjkt.mobilepqi.databinding.ActivitySigninBinding
import com.uinjkt.mobilepqi.ui.dashboard.activity.DashboardActivity
import com.uinjkt.mobilepqi.ui.kelas.daftarkelas.DaftarKelasActivity
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

    private val viewModel by viewModel<SigninViewModel>()

    override fun getViewBinding(): ActivitySigninBinding =
        ActivitySigninBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
        initListener()
        initObserver()
    }

    private fun initView() {
        binding.etNipNimSignin.doAfterTextChanged {
            it?.let {
                clearPasteTextFormatting(it)
            }
        }

        binding.etPasswordSignin.doAfterTextChanged {
            it?.let {
                clearPasteTextFormatting(it)
            }
        }

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
    }

    @SuppressLint("CheckResult")
    private fun initListener() {
        binding.tvBelumDaftarClick.setOnClickListener {
            SignupActivity.start(this)
            finish()
        }

        binding.tvLupaPassword.setOnClickListener {
            LupaPasswordActivity.start(this)
        }

        binding.btnSignin.setOnClickListener {
            signin()
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
                binding.etPasswordSignin.setError(
                    "Harap masukkan password Anda dengan benar!",
                    null
                )
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

    private fun initObserver() {
        viewModel.signin.observe(this) { model ->
            when (model) {
                is Resource.Loading -> {
                    showLoading(true)
                }
                is Resource.Success -> {
                    showLoading(false)
                    model.data?.let { actionAfterSignin(it) }
                }
                is Resource.Error -> {
                    showLoading(false)
                    if (model.message?.contains("not found", true) == true) {
                        showToast("Anda belum mempunyai akun")
                    } else if (model.message?.contains("wrong password", true) == true) {
                        showToast("Password yang Anda masukkan salah")
                    } else {
                        showToast(model.message ?: "Something Went Wrong")
                    }
                }
            }
        }
    }

    private fun actionAfterSignin(data: SigninModel) {
        viewModel.setToken(data.token)
        viewModel.setUserRole(data.role)
        if (data.role == "mahasiswa") {
            viewModel.setClassId(data.classId)
            DashboardActivity.start(this)
            finish()
        } else {
            DaftarKelasActivity.start(this)
            finish()
        }
    }

    private fun signin() {
        viewModel.signin(
            SigninPayload(
                nim = binding.etNipNimSignin.text.toString(),
                password = binding.etPasswordSignin.text.toString()
            )
        )
    }

    private fun showLoading(value: Boolean) {
        binding.progressBar.isVisible = value
        binding.btnSignin.isEnabled = !value
    }
}