package com.uinjkt.mobilepqi.ui.splashscreen

import android.content.Context
import android.content.Intent
import android.content.Intent.ACTION_GET_CONTENT
import android.net.Uri
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.mobilepqi.core.data.Resource
import com.uinjkt.mobilepqi.common.BaseActivity
import com.uinjkt.mobilepqi.databinding.ActivityOnboardingBinding
import com.uinjkt.mobilepqi.ui.dashboard.DashboardViewModel
import com.uinjkt.mobilepqi.util.uriToFile
import org.koin.androidx.viewmodel.ext.android.viewModel

class OnboardingActivity : BaseActivity<ActivityOnboardingBinding>() {
    companion object {
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, OnboardingActivity::class.java)
            context.startActivity(starter)
        }
    }

    private val viewModel by viewModel<DashboardViewModel>()

    override fun getViewBinding(): ActivityOnboardingBinding =
        ActivityOnboardingBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.imageUploaded.observe(this) { model ->
            when(model) {
                is Resource.Loading -> showToast("loading")
                is Resource.Success -> showToast(model.data?.imageUrl ?: "")
                is Resource.Error -> showToast(model.message ?: "")
            }
        }

        binding.btnMulai.setOnClickListener {
            //SigninActivity.start(this)
            //finish()
            startGallery()
        }
    }

    private fun startGallery() {
        val intent = Intent()
        intent.action = ACTION_GET_CONTENT
        //intent.type = "image/*"
        intent.type = "*/*"
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        val chooser = Intent.createChooser(intent, "Choose a PDF")
        launcherIntentGallery.launch(chooser)
    }

    private val launcherIntentGallery = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val selectedImg: Uri = result.data?.data as Uri

            val myFile = uriToFile(selectedImg, this)
            viewModel.uploadImage(myFile)
        }
    }
}