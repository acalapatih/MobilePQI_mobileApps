package com.uinjkt.mobilepqi.ui.dashboard

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.uinjkt.mobilepqi.common.BaseActivity
import com.uinjkt.mobilepqi.databinding.ActivityDashboardBinding
import com.uinjkt.mobilepqi.ui.profile.ProfileActivity

class DashboardActivity: BaseActivity<ActivityDashboardBinding>() {
    companion object {
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, ActivityDashboardBinding::class.java)
            context.startActivity(starter)
        }
    }

    override fun getViewBinding(): ActivityDashboardBinding = ActivityDashboardBinding.inflate(layoutInflater)

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        val user = binding.imgUser
        user.setOnClickListener { view ->
            val userIntent = Intent(view.context, ProfileActivity::class.java)
            startActivityForResult(userIntent, 0)
        }
    }
}