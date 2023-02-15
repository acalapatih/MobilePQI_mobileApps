package com.uinjkt.mobilepqi.ui.dosen.menusilabus

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.addCallback
import com.uinjkt.mobilepqi.R
import com.uinjkt.mobilepqi.common.BaseActivity
import com.uinjkt.mobilepqi.databinding.ActivityDosenSilabusBinding

class DosenSilabusActivity : BaseActivity<ActivityDosenSilabusBinding>() {
    companion object {
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, ActivityDosenSilabusBinding::class.java)
            context.startActivity(starter)
        }
    }

    override fun getViewBinding(): ActivityDosenSilabusBinding =
        ActivityDosenSilabusBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.ivCloseSilabusDosen.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        onBackPressedDispatcher.addCallback(this) {
            finish()
        }

        binding.btnTambahFile.setOnClickListener {
            showOneActionDialog(
                message = getString(R.string.tv_tambah_file_silabus_dialog),
                btnMessage = getString(R.string.btn_oke_text),
            )
        }

        binding.btnHapusFile.setOnClickListener {
            showTwoActionDialog(
                message = getString(R.string.tv_hapus_file_silabus_dialog),
                btnPositiveMessage = getString(R.string.btn_oke_text),
                btnNegativeMessage = getString(R.string.btn_batal_text),
                onPositiveButtonClicked = {
                }
            )
        }
    }
}