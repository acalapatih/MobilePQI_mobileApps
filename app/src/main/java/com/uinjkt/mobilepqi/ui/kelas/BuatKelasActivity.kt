package com.uinjkt.mobilepqi.ui.kelas

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.addCallback
import com.jakewharton.rxbinding2.widget.RxTextView
import com.uinjkt.mobilepqi.common.BaseActivity
import com.uinjkt.mobilepqi.databinding.ActivityBuatKelasBinding
import io.reactivex.Observable

class BuatKelasActivity : BaseActivity<ActivityBuatKelasBinding>() {
    companion object {
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, BuatKelasActivity::class.java)
            context.startActivity(starter)
        }
    }

    override fun getViewBinding(): ActivityBuatKelasBinding =
        ActivityBuatKelasBinding.inflate(layoutInflater)

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        val backIntent = binding.icClose
        backIntent.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
            onBackPressedDispatcher.addCallback(this) {
                finish()
            }
        }

        val btnSimpan = binding.btnSimpanDatakelas
        btnSimpan.setOnClickListener {
            DaftarKelasActivity.start(this)
        }

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