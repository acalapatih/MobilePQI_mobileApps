package com.uinjkt.mobilepqi.ui.mahasiswa.menusilabus

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.uinjkt.mobilepqi.R
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
        setContentView(R.layout.activity_mahasiswa_silabus)
    }
}