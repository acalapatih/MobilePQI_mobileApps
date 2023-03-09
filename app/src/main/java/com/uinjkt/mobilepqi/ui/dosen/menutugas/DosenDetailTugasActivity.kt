package com.uinjkt.mobilepqi.ui.dosen.menutugas

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.addCallback
import com.uinjkt.mobilepqi.common.BaseActivity
import com.uinjkt.mobilepqi.databinding.ActivityDosenDetailTugasBinding
import com.uinjkt.mobilepqi.ui.mahasiswa.MahasiswaFileUploadedByAdapter

class DosenDetailTugasActivity : BaseActivity<ActivityDosenDetailTugasBinding>(), MahasiswaFileUploadedByAdapter.OnUserClickListener {

    companion object {
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, DosenDetailTugasActivity::class.java)
            context.startActivity(starter)
        }
    }

    override fun getViewBinding(): ActivityDosenDetailTugasBinding = ActivityDosenDetailTugasBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.ivLogoBackCircleButtonTugasDosen.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
        onBackPressedDispatcher.addCallback(this) {
            finish()
        }

        binding.btnEditTugasDosen.setOnClickListener {
            DosenBuatEditTugasActivity.start(this@DosenDetailTugasActivity, "edit", 1)
        }

        binding.btnCekTugasMahasiswa.setOnClickListener {
            DosenCekTugasMahasiswaActivity.start(this@DosenDetailTugasActivity)
        }

    }

    override fun onUserClickListener(action: String) {
        if (action == "delete") {
            showToast("File Deleted", Toast.LENGTH_SHORT)
        } else {
            showToast("File Downloaded", Toast.LENGTH_SHORT)
        }
    }
}