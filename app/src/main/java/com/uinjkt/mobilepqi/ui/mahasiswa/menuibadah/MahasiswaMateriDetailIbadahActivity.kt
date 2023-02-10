package com.uinjkt.mobilepqi.ui.mahasiswa.menuibadah

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.addCallback
import com.uinjkt.mobilepqi.R
import com.uinjkt.mobilepqi.common.BaseActivity
import com.uinjkt.mobilepqi.databinding.ActivityMahasiswaMateriDetailBinding

class MahasiswaMateriDetailIbadahActivity : BaseActivity<ActivityMahasiswaMateriDetailBinding>() {

    companion object {
        @JvmStatic
        fun start(context: Context, position: Int, title: String) {
            val starter = Intent(context, MahasiswaMateriDetailIbadahActivity::class.java)
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

        val idMateriDetailIbadah = intent.getIntExtra(ID, 0)
        val titleMateriDetailIbadah = intent.getStringExtra("title")

        binding.tvTitleMenuDetailQiroah.text = getString(R.string.tv_title_detail_materi_ibadah, titleMateriDetailIbadah)
        binding.tvNameDocument.text = getString(R.string.tv_document_name_pdf, idMateriDetailIbadah)
        binding.tvDescriptionMenuDetail.text = getString(R.string.tv_description_materi, titleMateriDetailIbadah)

        binding.ivLogoBackCircleButton.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        onBackPressedDispatcher.addCallback(this) {
            finish()
        }
    }
}