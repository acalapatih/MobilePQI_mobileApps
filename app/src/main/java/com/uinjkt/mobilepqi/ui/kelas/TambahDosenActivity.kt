package com.uinjkt.mobilepqi.ui.kelas

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.addCallback
import androidx.core.view.isInvisible
import com.uinjkt.mobilepqi.R
import com.uinjkt.mobilepqi.common.BaseActivity
import com.uinjkt.mobilepqi.databinding.ActivityTambahDosenBinding

class TambahDosenActivity: BaseActivity<ActivityTambahDosenBinding>() {
    companion object {
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, TambahDosenActivity::class.java)
            context.startActivity(starter)
        }
    }

    override fun getViewBinding(): ActivityTambahDosenBinding =
        ActivityTambahDosenBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()

        val backIcon = binding.icBackWhite
        backIcon.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
            onBackPressedDispatcher.addCallback(this) {
                finish()
            }
        }

        val tambahDosenIcon = binding.icTambahDosen
        tambahDosenIcon.isInvisible = true

//        val pilihDosenIcon = binding.icPilihDosen
//        pilihDosenIcon.isInvisible = true

        val dosen = binding.rvTambahDosen
        dosen.setOnClickListener {
            tambahDosenIcon.isInvisible = false
//            pilihDosenIcon.isInvisible = false
        }

        binding.icTambahDosen.setOnClickListener {
            showOneActionDialog(
                message = getString(R.string.message_tambah_dosen),
                btnMessage = getString(R.string.btnMessage_tambah_dosen)
            )
        }
    }
}