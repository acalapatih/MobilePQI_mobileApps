package com.uinjkt.mobilepqi.ui.pengaturan

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.addCallback
import com.uinjkt.mobilepqi.R
import com.uinjkt.mobilepqi.common.BaseActivity
import com.uinjkt.mobilepqi.databinding.ActivityProfilPengaturanBahasaBinding

class ProfilPengaturanBahasaActivity : BaseActivity<ActivityProfilPengaturanBahasaBinding>() {
    companion object {
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, ProfilPengaturanBahasaActivity::class.java)
            context.startActivity(starter)
        }
    }

    override fun getViewBinding(): ActivityProfilPengaturanBahasaBinding =
        ActivityProfilPengaturanBahasaBinding.inflate(layoutInflater)

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.icBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        onBackPressedDispatcher.addCallback(this) {
            finish()
        }

        binding.rbBahasaIndo.setOnClickListener {
            binding.btnTerapkan.isEnabled = true
        }

        binding.btnTerapkan.setOnClickListener {
            showOneActionDialog(
                message = getString(R.string.message_btnTerapkan),
                btnMessage = getString(R.string.btnMessage_btnTerapkan)
            )
        }
    }
}