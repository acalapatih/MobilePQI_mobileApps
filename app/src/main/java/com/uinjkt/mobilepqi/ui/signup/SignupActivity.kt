package com.uinjkt.mobilepqi.ui.signup

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.util.Patterns
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import com.jakewharton.rxbinding2.widget.RxTextView
import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.data.source.remote.response.signup.SignupPayload
import com.uinjkt.mobilepqi.R
import com.uinjkt.mobilepqi.common.BaseActivity
import com.uinjkt.mobilepqi.databinding.ActivitySignupBinding
import com.uinjkt.mobilepqi.ui.signin.SigninActivity
import io.reactivex.Observable
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignupActivity : BaseActivity<ActivitySignupBinding>() {
    companion object {
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, SignupActivity::class.java)
            context.startActivity(starter)
        }
    }

    private val viewModel by viewModel<SignupViewModel>()

    override fun getViewBinding(): ActivitySignupBinding =
        ActivitySignupBinding.inflate(layoutInflater)

    private var tipeAkun: String = "dosen"

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
        initListener()
        initObserver()
    }

    private fun initView() {
        binding.etNamaSignup.doAfterTextChanged {
            it?.let {
                clearPasteTextFormatting(it)
            }
        }

        binding.etNipNimSignup.doAfterTextChanged {
            it?.let {
                clearPasteTextFormatting(it)
            }
        }

        binding.etKodeKelasSignup.doAfterTextChanged {
            it?.let {
                clearPasteTextFormatting(it)
            }
        }

        binding.etEmailSignup.doAfterTextChanged {
            it?.let {
                clearPasteTextFormatting(it)
            }
        }

        binding.etPasswordSignup.doAfterTextChanged {
            it?.let {
                clearPasteTextFormatting(it)
            }
        }

        // show hide password with eye icon
        var isSelected = true
        binding.ivShowHidePassword.setOnClickListener {
            if (isSelected) {
                binding.ivShowHidePassword.setImageResource(R.drawable.ic_eye_hide_password)
                val start = binding.etPasswordSignup.selectionStart
                val end = binding.etPasswordSignup.selectionEnd
                binding.etPasswordSignup.transformationMethod = null
                binding.etPasswordSignup.setSelection(start, end)
                isSelected = false
            } else {
                binding.ivShowHidePassword.setImageResource(R.drawable.ic_eye_show_password)
                val start = binding.etPasswordSignup.selectionStart
                val end = binding.etPasswordSignup.selectionEnd
                binding.etPasswordSignup.transformationMethod = PasswordTransformationMethod()
                binding.etPasswordSignup.setSelection(start, end)
                isSelected = true
            }
        }
    }

    @SuppressLint("CheckResult")
    private fun initListener() {
        binding.tvSudahDaftarClick.setOnClickListener {
            SigninActivity.start(this)
            finish()
        }

        binding.btnSignup.setOnClickListener {
            signup()
        }

        val namaStream = RxTextView.textChanges(binding.etNamaSignup)
            .skipInitialValue()
            .map { nama ->
                namaValidate(nama.toString()) && nama.length > 2
            }
        namaStream.subscribe { isNamaValid ->
            if (!isNamaValid) {
                binding.etNamaSignup.error = "Harap masukkan nama Anda dengan benar!"
            }
        }

        val emailStream = RxTextView.textChanges(binding.etEmailSignup)
            .skipInitialValue()
            .map { email ->
                // regex email
                Patterns.EMAIL_ADDRESS.matcher(email).matches() && email.length > 5
            }
        emailStream.subscribe { isEmailValid ->
            if (!isEmailValid) {
                binding.etEmailSignup.error = "Harap masukkan email Anda dengan benar!"
            }
        }

        val nimNipStream = RxTextView.textChanges(binding.etNipNimSignup)
            .skipInitialValue()
            .map { nidn_nip_nim ->
                nidn_nip_nim.length > 5
            }
        nimNipStream.subscribe { isNimNipValid ->
            if (!isNimNipValid) {
                binding.etNipNimSignup.error = "Harap masukkan NIDN/NIP/NIM Anda dengan benar!"
            }
        }

        val cekKodeKelasStream = RxTextView.textChanges(binding.etNipNimSignup)
            .skipInitialValue()
            .map { nidn_nip_nim ->
                nidn_nip_nim.length
            }
        cekKodeKelasStream.subscribe { length ->
            when (length) {
                14 -> {
                    tipeAkun = "mahasiswa"
                    binding.etKodeKelasSignup.text.clear()
                    binding.tvKodeKelasSignup.isGone = false
                    binding.etKodeKelasSignup.isGone = false
                    binding.btnSignup.isEnabled = false
                }
                else -> {
                    tipeAkun = "dosen"
                    binding.etKodeKelasSignup.text.clear()
                    binding.tvKodeKelasSignup.isGone = true
                    binding.etKodeKelasSignup.isGone = true
                }
            }
        }

        val kodeKelasStream = RxTextView.textChanges(binding.etKodeKelasSignup)
            .skipInitialValue()
            .map { kodeKelas ->
                kodeKelas.length == 6 && kodeKelasValidate(kodeKelas.toString())
            }
        kodeKelasStream.subscribe { isKodeKelasValid ->
            if (!isKodeKelasValid) {
                binding.etKodeKelasSignup.error = "Harap masukkan Kode Kelas Anda dengan benar!"
            }
        }

        val passwordStream = RxTextView.textChanges(binding.etPasswordSignup)
            .skipInitialValue()
            .map { password ->
                passwordValidate(password.toString())
            }
        passwordStream.subscribe { isPasswordValid ->
            if (!isPasswordValid) {
                binding.etPasswordSignup.setError(
                    "Password harus mengandung minimal 6 karakter yang terdiri dari 1 huruf besar, 1 huruf kecil, 1 angka, dan 1 karakter spesial",
                    null
                )
            }
        }

        Observable.combineLatest(
            namaStream,
            emailStream,
            nimNipStream,
            kodeKelasStream,
            passwordStream
        ) { namaValid: Boolean, emailValid: Boolean, nimNipValid: Boolean, kodeKelasValid: Boolean, passwordValid: Boolean ->
            namaValid && emailValid && nimNipValid && kodeKelasValid && passwordValid
        }.subscribe { isButtonValid ->
            if (tipeAkun == "mahasiswa") {
                binding.btnSignup.isEnabled = isButtonValid
            }
        }

        Observable.combineLatest(
            namaStream,
            emailStream,
            nimNipStream,
            passwordStream
        ) { namaValid: Boolean, emailValid: Boolean, nimNipValid: Boolean, passwordValid: Boolean ->
            namaValid && emailValid && nimNipValid && passwordValid
        }.subscribe { isButtonValid ->
            if (tipeAkun == "dosen") {
                binding.btnSignup.isEnabled = isButtonValid
            }
        }
    }

    private fun initObserver() {
        viewModel.signup.observe(this) { model ->
            when (model) {
                is Resource.Loading -> {
                    showLoading(true)
                }
                is Resource.Success -> {
                    showLoading(false)
                    actionAfterSignup()
                }
                is Resource.Error -> {
                    showLoading(false)
                    showToast(model.message ?: "Something Went Wrong")
                }
            }
        }
    }

    private fun namaValidate(nama: String): Boolean {
        val namaPattern = "^[a-zA-Z\\s]+$"
        return nama.matches(namaPattern.toRegex())
    }

    private fun kodeKelasValidate(kode: String): Boolean {
        val kelasPattern = "^[\\S]+$"
        return kode.matches(kelasPattern.toRegex())
    }

    private fun passwordValidate(password: String): Boolean {
        val passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*\\W)(?!.*\\s).{6,}$"
        return password.matches(passwordPattern.toRegex())
    }

    private fun actionAfterSignup() {
        showToast("Berhasil mendaftar! Silakan aktivasi akun melalui email.")
        SigninActivity.start(this)
        finish()
    }

    private fun signup() {
        viewModel.signup(
            SignupPayload(
                name = binding.etNamaSignup.text.toString(),
                nim = binding.etNipNimSignup.text.toString(),
                email = binding.etEmailSignup.text.toString(),
                password = binding.etPasswordSignup.text.toString(),
                classCode = binding.etKodeKelasSignup.text.toString()
            )
        )
    }

    private fun showLoading(value: Boolean) {
        binding.progressBar.isVisible = value
        binding.btnSignup.isEnabled = !value
    }
}