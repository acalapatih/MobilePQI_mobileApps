package com.uinjkt.mobilepqi.ui.lupapassword

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import androidx.activity.addCallback
import androidx.core.view.isVisible
import com.jakewharton.rxbinding2.widget.RxTextView
import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.data.source.remote.response.lupapassword.LupaPasswordPayload
import com.uinjkt.mobilepqi.R
import com.uinjkt.mobilepqi.common.BaseActivity
import com.uinjkt.mobilepqi.databinding.ActivityLupaPasswordBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class LupaPasswordActivity : BaseActivity<ActivityLupaPasswordBinding>() {
    companion object {
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, LupaPasswordActivity::class.java)
            context.startActivity(starter)
        }
    }

    private val viewModel by viewModel<LupaPasswordViewModel>()

    override fun getViewBinding(): ActivityLupaPasswordBinding =
        ActivityLupaPasswordBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initListener()
        initObserver()
    }

    @SuppressLint("CheckResult")
    private fun initListener() {
        binding.ivCloseLupaPassword.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding.btnKirimLupaPassword.setOnClickListener {
            lupaPassword()
        }

        onBackPressedDispatcher.addCallback(this) {
            finish()
        }

        val emailStream = RxTextView.textChanges(binding.etEmailLupaPassword)
            .skipInitialValue()
            .map { email ->
                Patterns.EMAIL_ADDRESS.matcher(email).matches() && email.length > 5
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

    private fun initObserver() {
        viewModel.lupaPassword.observe(this) { model ->
            when (model) {
                is Resource.Loading -> {
                    showLoading(true)
                }
                is Resource.Success -> {
                    showLoading(false)
                    actionAfterLupaPassword()
                }
                is Resource.Error -> {
                    showLoading(false)
                    showToast(model.message ?: "")
                }
            }
        }
    }

    private fun lupaPassword() {
        viewModel.lupaPassword(
            LupaPasswordPayload(
                email = binding.etEmailLupaPassword.text.toString(),
            )
        )
    }

    private fun actionAfterLupaPassword() {
        showOneActionDialogWithInvoke(
            message = getString(R.string.btn_kirim_lupa_password_deksripsi_text),
            btnMessage = getString(R.string.btn_oke_text),
            onButtonClicked = {
                onBackPressedDispatcher.onBackPressed()
            }
        )
    }

    private fun showLoading(value: Boolean) {
        binding.progressBar.isVisible = value
        binding.btnKirimLupaPassword.isEnabled = !value
    }
}