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
            val starter = Intent(context,ActivityDosenSilabusBinding::class.java)
            context.startActivity(starter)
        }
    }

    override fun getViewBinding(): ActivityDosenSilabusBinding =
        ActivityDosenSilabusBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dosen_silabus)

        binding.ivCloseSilabusDosen.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        onBackPressedDispatcher.addCallback(this) {
            finish()
        }
    }
}