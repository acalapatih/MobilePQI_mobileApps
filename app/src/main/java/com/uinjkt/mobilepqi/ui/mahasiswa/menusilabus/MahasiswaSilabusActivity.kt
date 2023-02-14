package com.uinjkt.mobilepqi.ui.mahasiswa.menusilabus

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.addCallback
import com.uinjkt.mobilepqi.common.BaseActivity
import com.uinjkt.mobilepqi.databinding.ActivityMahasiswaSilabusBinding


class MahasiswaSilabusActivity : BaseActivity<ActivityMahasiswaSilabusBinding>() {
    companion object {
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, ActivityMahasiswaSilabusBinding::class.java)
            context.startActivity(starter)
        }
    }

    override fun getViewBinding(): ActivityMahasiswaSilabusBinding =
        ActivityMahasiswaSilabusBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.ivCloseSilabusMahasiswa.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        onBackPressedDispatcher.addCallback(this) {
            finish()
        }

        binding.wvSilabusPdf.settings.javaScriptEnabled = true
        binding.wvSilabusPdf.settings.builtInZoomControls = true
        binding.wvSilabusPdf.loadUrl("https://docs.google.com/gview?embedded=true&url=" + "https://www.orimi.com/pdf-test.pdf")
    }
}