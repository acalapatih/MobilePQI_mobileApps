package com.uinjkt.mobilepqi.ui.mahasiswa.menusilabus

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
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
        initWebView()

        binding.ivCloseSilabusMahasiswa.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        onBackPressedDispatcher.addCallback(this) {
            finish()
        }
    }

    private fun initWebView() {
        with(binding.wvSilabusPdf) {
            settings.javaScriptEnabled = true
            loadUrl("https://docs.google.com/gview?embedded=true&url=" + "https://www.orimi.com/pdf-test.pdf")
            webViewClient= object : WebViewClient() {
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