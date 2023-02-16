package com.uinjkt.mobilepqi.ui.profil

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.uinjkt.mobilepqi.R
import com.uinjkt.mobilepqi.common.BaseActivity
import com.uinjkt.mobilepqi.databinding.ActivityProfilBinding
import com.uinjkt.mobilepqi.ui.dashboard.DashboardActivity
import com.uinjkt.mobilepqi.ui.pengaturan.ProfilePengaturanActivity

class ProfileActivity : BaseActivity<ActivityProfilBinding>() {
    /** Called when the activity is first created.  */
    companion object {
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, ProfileActivity::class.java)
            context.startActivity(starter)
        }
    }

    override fun getViewBinding(): ActivityProfilBinding = ActivityProfilBinding.inflate(layoutInflater)

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        val close = binding.icClose
        close.setOnClickListener {view ->
            DashboardActivity.start(this)
        }

        binding.tvProfil.setOnClickListener { view ->
            ProfileInfoActivity.start(this)
        }

        binding.tvPengaturan.setOnClickListener { view ->
            ProfilePengaturanActivity.start(this)
        }

        binding.tvLogout.setOnClickListener{
            showTwoActionDialog(
                message = getString(R.string.message_logout),
                btnPositiveMessage = getString(R.string.btnPositive_logout),
                btnNegativeMessage = getString(R.string.btnNegative_logout),
                onPositiveButtonClicked = {System.exit(0)}
            )
        }
    }
}