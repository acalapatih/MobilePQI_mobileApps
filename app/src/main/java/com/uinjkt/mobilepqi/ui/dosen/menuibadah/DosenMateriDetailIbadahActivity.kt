package com.uinjkt.mobilepqi.ui.dosen.menuibadah

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.view.ViewGroup
import android.view.Window
import androidx.activity.addCallback
import com.uinjkt.mobilepqi.R
import com.uinjkt.mobilepqi.common.BaseActivity
import com.uinjkt.mobilepqi.databinding.ActivityDosenMateriDetailBinding
import com.uinjkt.mobilepqi.databinding.ItemDialogOneActionThinFontBinding

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
                    showOneActionThinFontDialogInvoke("Materi Berhasil Dihapus", "Okay") {
                        onBackPressedDispatcher.onBackPressed()
                    }
                })
        }

    }

    private fun showOneActionThinFontDialogInvoke(message: String, btnMessage: String, onButtonClicked: () -> Unit) {
        val dialog = Dialog(this)
        val dialogBinding = ItemDialogOneActionThinFontBinding.inflate(layoutInflater)
        val window = dialog.window

        window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(dialogBinding.root)

        val deviceWidth: Int = Resources.getSystem().displayMetrics.widthPixels
        val margin = (60 * Resources.getSystem().displayMetrics.density).toInt()
        val width: Int = deviceWidth - margin
        window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog.setCancelable(false)

        with(dialogBinding) {
            tvMessage.text = message
            btnOkay.text = btnMessage
            btnOkay.setOnClickListener {
                onButtonClicked.invoke()
                dialog.dismiss()
            }

        }

        dialog.show()
    }
}