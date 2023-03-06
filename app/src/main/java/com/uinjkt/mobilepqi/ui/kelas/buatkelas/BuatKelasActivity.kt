package com.uinjkt.mobilepqi.ui.kelas.buatkelas

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.addCallback
import androidx.core.view.isVisible
import com.jakewharton.rxbinding2.widget.RxTextView
import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.data.source.remote.response.buatkelas.BuatKelasPayload
import com.uinjkt.mobilepqi.common.BaseActivity
import com.uinjkt.mobilepqi.databinding.ActivityBuatKelasBinding
import io.reactivex.Observable
import org.koin.androidx.viewmodel.ext.android.viewModel

class BuatKelasActivity : BaseActivity<ActivityBuatKelasBinding>() {
    companion object {
        @JvmStatic
        fun start(context: Context): Intent {
            val starter = Intent(context, BuatKelasActivity::class.java)
            return starter
        }
    }

    private val viewModel by viewModel<BuatKelasViewModel>()

    override fun getViewBinding(): ActivityBuatKelasBinding =
        ActivityBuatKelasBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initListener()
        initObserver()
    }

    private fun initObserver() {
        viewModel.buatkelas.observe(this) { model ->
            when (model) {
                is Resource.Loading -> {
                    showLoading(true)
                }
                is Resource.Success -> {
                    showLoading(false)
                    setResult(Activity.RESULT_OK)
                    finish()
                }
                is Resource.Error -> {
                    showLoading(false)
                    showToast(model.message ?: "")
                }
            }
        }
    }

    private fun buatkelas() {
        viewModel.buatkelas(
            BuatKelasPayload(
                name = binding.etEditNamakelas.text.toString(),
                ruang = binding.etEditRuangkelas.text.toString(),
                jadwal = binding.etEditJadwalkelas.text.toString()
            )
        )
    }

    private fun showLoading(value: Boolean) {
        binding.progressBar.isVisible = value
        binding.btnSimpanDatakelas.isEnabled = !value
    }

    private fun initListener() {
        binding.icClose.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
        onBackPressedDispatcher.addCallback(this) {
            finish()
        }
        binding.btnSimpanDatakelas.setOnClickListener {
            buatkelas()
        }
    }

    @SuppressLint("CheckResult")
    private fun initView() {
        val namakelasStream = RxTextView.textChanges(binding.etEditNamakelas)
            .skipInitialValue()
            .map { namakelas ->
                namakelas.isNotEmpty()
            }
        namakelasStream.subscribe { isUserValid ->
            if (!isUserValid) {
                binding.etEditNamakelas.error = "Harap masukkan Nama Kelas dengan benar!"
            }
        }

        val ruangkelasStream = RxTextView.textChanges(binding.etEditRuangkelas)
            .skipInitialValue()
            .map { ruangkelas ->
                ruangkelas.isNotEmpty()
            }
        ruangkelasStream.subscribe { isUserValid ->
            if (!isUserValid) {
                binding.etEditRuangkelas.error = "Harap masukkan Ruang Kelas dengan benar!"
            }
        }

        val jadwalkelasStream = RxTextView.textChanges(binding.etEditJadwalkelas)
            .skipInitialValue()
            .map { jadwalkelas ->
                jadwalkelas.isNotEmpty()
            }
        jadwalkelasStream.subscribe { isUserValid ->
            if (!isUserValid) {
                binding.etEditJadwalkelas.error = "Harap masukkan Jadwal Kelas dengan benar!"
            }
        }

        Observable.combineLatest(
            namakelasStream,
            ruangkelasStream,
            jadwalkelasStream
        ) { namakelasValid: Boolean, ruangkelasValid: Boolean, jadwalkelasValid ->
            namakelasValid && ruangkelasValid && jadwalkelasValid
        }.subscribe { isButtonValid ->
            binding.btnSimpanDatakelas.isEnabled = isButtonValid
        }
    }
}