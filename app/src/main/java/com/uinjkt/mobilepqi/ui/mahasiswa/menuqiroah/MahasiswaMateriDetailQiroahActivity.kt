package com.uinjkt.mobilepqi.ui.mahasiswa.menuqiroah

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.addCallback
import com.uinjkt.mobilepqi.R
import com.uinjkt.mobilepqi.common.BaseActivity
import com.uinjkt.mobilepqi.databinding.ActivityMahasiswaMateriDetailBinding

class MahasiswaMateriDetailQiroahActivity : BaseActivity<ActivityMahasiswaMateriDetailBinding>() {

    companion object {
        @JvmStatic
        fun start(context: Context, position: Int, title: String) {
            val starter = Intent(context, MahasiswaMateriDetailQiroahActivity::class.java)
                .putExtra(ID, position)
                .putExtra(TITLE, title)
            context.startActivity(starter)
        }

        private const val ID = "id"
        private const val TITLE = "title"
    }

    override fun getViewBinding(): ActivityMahasiswaMateriDetailBinding = ActivityMahasiswaMateriDetailBinding.inflate(layoutInflater)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val idMateriDetailQiroah = intent.getIntExtra(ID, 0)
        val titleMateriDetailQiroah = intent.getStringExtra("title")

        binding.tvTitleMenuDetailQiroah.text = getString(R.string.tv_title_detail_materi_qiroah, titleMateriDetailQiroah)
        binding.tvNameDocument.text = getString(R.string.tv_document_name_pdf, idMateriDetailQiroah)
        binding.tvDescriptionMenuDetail.text = getString(R.string.tv_description_materi, titleMateriDetailQiroah)

        binding.ivLogoBackCircleButton.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        onBackPressedDispatcher.addCallback(this) {
            finish()
        }
    }
}