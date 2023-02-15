package com.uinjkt.mobilepqi.ui.dosen.menuibadah

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.addCallback
import com.uinjkt.mobilepqi.R
import com.uinjkt.mobilepqi.common.BaseActivity
import com.uinjkt.mobilepqi.databinding.ActivityDosenMateriDetailBinding

class DosenMateriDetailIbadahActivity : BaseActivity<ActivityDosenMateriDetailBinding>() {
    companion object {
        @JvmStatic
        fun start(context: Context, position: Int, title: String) {
            val starter = Intent(context, DosenMateriDetailIbadahActivity::class.java)
                .putExtra(ID, position)
                .putExtra(TITLE, title)
            context.startActivity(starter)
        }
        private const val ID = "id"
        private const val TITLE = "title"
    }

    override fun getViewBinding(): ActivityDosenMateriDetailBinding =
        ActivityDosenMateriDetailBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val idMateriDetailIbadah = intent.getIntExtra(ID, 0)
        val titleMateriDetailIbadah = intent.getStringExtra(TITLE)

        binding.tvTitleMenuDetailDosen.text =
            getString(R.string.tv_title_detail_materi_ibadah, titleMateriDetailIbadah)
        binding.tvNameDocument.text = getString(R.string.tv_document_name_pdf, idMateriDetailIbadah)
        binding.ivLogoBackCircleButtonDosen.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        onBackPressedDispatcher.addCallback(this) {
            finish()
        }

        binding.btnSimpanMateriDosen.setOnClickListener {
            showOneActionThinFontDialog("Materi Berhasil Disimpan", "Okay")
        }

        binding.btnHapusMateriDosen.setOnClickListener {
            showTwoActionDialog(
                "Yakin Hapus Materi?",
                "Hapus Materi",
                true,
                "Ya",
                "Tidak",
                onPositiveButtonClicked = {
                    showOneActionThinFontDialog("Materi Berhasil Dihapus", "Okay")
                })
        }

    }
}