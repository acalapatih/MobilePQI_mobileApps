package com.uinjkt.mobilepqi.ui.signup

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.util.Patterns
import androidx.core.view.isGone
import com.jakewharton.rxbinding2.widget.RxTextView
import com.uinjkt.mobilepqi.R
import com.uinjkt.mobilepqi.common.BaseActivity
import com.uinjkt.mobilepqi.databinding.ActivitySignupBinding
import com.uinjkt.mobilepqi.ui.signin.SigninActivity
import io.reactivex.Observable

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

    private var type: String = ""

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // show hide password with eye icon
        var isSelected = true
        binding.ivShowHidePassword.setOnClickListener {
            if (isSelected) {
                binding.ivShowHidePassword.setImageResource(R.drawable.ic_eye_hide_password)
                val start = binding.etPasswordSignup.selectionStart
                val end = binding.etPasswordSignup.selectionEnd
                binding.etPasswordSignup.transformationMethod = null
                binding.etPasswordSignup.setSelection(start, end)
                isSelected = false;
            } else {
                binding.ivShowHidePassword.setImageResource(R.drawable.ic_eye_show_password)
                val start = binding.etPasswordSignup.selectionStart
                val end = binding.etPasswordSignup.selectionEnd
                binding.etPasswordSignup.transformationMethod = PasswordTransformationMethod()
                binding.etPasswordSignup.setSelection(start, end)
                isSelected = true;
            }
        }

        binding.tvSudahDaftarClick.setOnClickListener {
            SigninActivity.start(this)
            finish()
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
                    type = "mahasiswa"
                    binding.tvKodeKelasSignup.isGone = false
                    binding.etKodeKelasSignup.isGone = false
                }
                else -> {
                    type = "dosen"
                    binding.tvKodeKelasSignup.isGone = true
                    binding.etKodeKelasSignup.isGone = true
                }
            }
        }

        val kodeKelasStream = RxTextView.textChanges(binding.etKodeKelasSignup)
            .skipInitialValue()
            .map { kodeKelas ->
                kodeKelas.length == 6
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

        if (type == "mahasiswa") {
            Observable.combineLatest(
                namaStream,
                emailStream,
                nimNipStream,
                kodeKelasStream,
                passwordStream
            ) { namaValid: Boolean, emailValid: Boolean, nimNipValid: Boolean, kodeKelasValid: Boolean, passwordValid: Boolean ->
                namaValid && emailValid && nimNipValid && kodeKelasValid && passwordValid
            }.subscribe { isButtonValid ->
                binding.btnSignup.isEnabled = isButtonValid
            }
        } else {
            Observable.combineLatest(
                namaStream,
                emailStream,
                nimNipStream,
                passwordStream
            ) { namaValid: Boolean, emailValid: Boolean, nimNipValid: Boolean, passwordValid: Boolean ->
                namaValid && emailValid && nimNipValid && passwordValid
            }.subscribe { isButtonValid ->
                binding.btnSignup.isEnabled = isButtonValid
            }
        }
    }

    private fun namaValidate(nama: String): Boolean {
        val namaPattern = "^[a-zA-Z\\s]+$"
        return nama.matches(namaPattern.toRegex())
    }

    private fun passwordValidate(password: String): Boolean {
        val passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*\\W)(?!.*\\s).{6,}$"
        return password.matches(passwordPattern.toRegex())
    }
}