package com.uinjkt.mobilepqi.ui.mahasiswa.menusilabus

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.addCallback
import androidx.core.view.isVisible
import com.mobilepqi.core.data.Resource
import com.uinjkt.mobilepqi.common.BaseActivity
import com.uinjkt.mobilepqi.databinding.ActivityMahasiswaSilabusBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class MahasiswaSilabusActivity : BaseActivity<ActivityMahasiswaSilabusBinding>() {
    companion object {
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, MahasiswaSilabusActivity::class.java)
            context.startActivity(starter)
        }
    }

    override fun getViewBinding(): ActivityMahasiswaSilabusBinding =
        ActivityMahasiswaSilabusBinding.inflate(layoutInflater)

    private val viewModel by viewModel<MahasiswaSilabusViewModel>()

    private var urlSilabus = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
        initListener()
        initObserver()
    }

    private fun initView() {
        getSilabus(1)
    }

    private fun initListener() {
        binding.ivCloseSilabusMahasiswa.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        onBackPressedDispatcher.addCallback(this) {
            finish()
        }
    }

    private fun initObserver() {
        viewModel.getSilabus.observe(this) { model ->
            when (model) {
                is Resource.Loading -> {
                    showLoading(true)
                }
                is Resource.Success -> {
                    showLoading(false)
                    if (model.data?.silabus?.isNotEmpty() == true) {
                        urlSilabus = model.data?.silabus ?: ""
                        binding.tvEmptySilabus.isVisible = false
                        Log.d("urlSilabus", urlSilabus)
                        initWebView()
                    } else {
                        binding.tvEmptySilabus.isVisible = true
                    }
                }
                is Resource.Error -> {
                    showLoading(false)
                    showToast(model.message ?: "Something Went Wrong")
                }
            }
        }
    }

    private fun getSilabus(idKelas: Int) {
        viewModel.getSilabus(idKelas = 1)
    }

    private fun showLoading(value: Boolean) {
        binding.progressBar.isVisible = value
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebView() {
        with(binding.wvSilabusPdf) {
            settings.loadWithOverviewMode = true
            settings.useWideViewPort = true
            settings.javaScriptEnabled = true
            settings.builtInZoomControls = true
            settings.displayZoomControls = false
            loadUrl("https://docs.google.com/gview?embedded=true&url=$urlSilabus")
            webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(
                    view: WebView?,
                    request: WebResourceRequest?
                ): Boolean {
                    view?.loadUrl(request?.url.toString())
                    return true
                }
            }
        }
    }
}