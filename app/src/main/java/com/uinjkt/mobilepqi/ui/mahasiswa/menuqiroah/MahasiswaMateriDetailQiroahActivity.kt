package com.uinjkt.mobilepqi.ui.mahasiswa.menuqiroah

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.uinjkt.mobilepqi.R
import com.uinjkt.mobilepqi.common.BaseActivity
import com.uinjkt.mobilepqi.databinding.ActivityMahasiswaMateriDetailBinding

class MahasiswaMateriDetailQiroahActivity : BaseActivity<ActivityMahasiswaMateriDetailBinding>() {

    companion object {
        @JvmStatic
        fun start(context: Context, position: Int, title: String) {
            val starter = Intent(context, MahasiswaMateriDetailQiroahActivity::class.java)
                .putExtra("idPosition", position)
                .putExtra("title", title)
            context.startActivity(starter)
        }
    }

    override fun getViewBinding(): ActivityMahasiswaMateriDetailBinding = ActivityMahasiswaMateriDetailBinding.inflate(layoutInflater)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()

        val bundle : Bundle? = intent.extras
        binding.tvTitleMenuDetailQiroah.text = getString(R.string.tv_title_detail_materi_qiroah, bundle!!.getString("title"))
        binding.tvNameDocument.text = getString(R.string.tv_document_name_pdf, bundle.getInt("idPosition"))
        binding.tvDescriptionMenuDetail.text = getString(R.string.tv_description_materi, bundle.getString("title"))
        binding.ivLogoBackCircleButton.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }
}