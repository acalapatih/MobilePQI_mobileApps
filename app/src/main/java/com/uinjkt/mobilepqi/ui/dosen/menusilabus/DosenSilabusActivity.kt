package com.uinjkt.mobilepqi.ui.dosen.menusilabus

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.addCallback
import androidx.activity.result.contract.ActivityResultContracts
import com.uinjkt.mobilepqi.R
import com.uinjkt.mobilepqi.common.BaseActivity
import com.uinjkt.mobilepqi.databinding.ActivityDosenSilabusBinding
import com.uinjkt.mobilepqi.util.Constant
import com.uinjkt.mobilepqi.util.openFileManagerPdf
import com.uinjkt.mobilepqi.util.uriToFile
import org.koin.androidx.viewmodel.ext.android.viewModel

class DosenSilabusActivity : BaseActivity<ActivityDosenSilabusBinding>() {
    companion object {
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, DosenSilabusActivity::class.java)
            context.startActivity(starter)
        }
    }

    private val viewModel by viewModel<DosenSilabusViewModel>()

    override fun getViewBinding(): ActivityDosenSilabusBinding =
        ActivityDosenSilabusBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.ivCloseSilabusDosen.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        onBackPressedDispatcher.addCallback(this) {
            finish()
        }

        binding.btnTambahFile.setOnClickListener {
            openFileManagerPdf(launcherIntentFile)
            showOneActionDialog(
                message = getString(R.string.tv_tambah_file_silabus_dialog),
                btnMessage = getString(R.string.btn_oke_text),
            )
        }

        binding.btnHapusFile.setOnClickListener {
            showTwoActionDialog(
                message = getString(R.string.tv_hapus_file_silabus_dialog),
                btnPositiveMessage = getString(R.string.btn_oke_text),
                btnNegativeMessage = getString(R.string.btn_batal_text),
                onPositiveButtonClicked = {
                }
            )
        }
    }

    private val launcherIntentFile = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val selectedFile: Uri = result.data?.data as Uri
            val myFile = uriToFile(selectedFile, this, "pdf")
            viewModel.uploadFilePDF(Constant.UPLOAD_KEY.SILABUS, Constant.UPLOAD_TYPE.FILE, myFile)
        }
    }
}