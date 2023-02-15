package com.uinjkt.mobilepqi.ui.kelas

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.addCallback
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.uinjkt.mobilepqi.R
import com.uinjkt.mobilepqi.common.BaseActivity
import com.uinjkt.mobilepqi.data.DataDosen
import com.uinjkt.mobilepqi.data.DataSourceKelasDosenMahasiswa
import com.uinjkt.mobilepqi.databinding.ActivityTambahDosenBinding
import com.uinjkt.mobilepqi.ui.kelas.adapter.TambahDosenAdapter

class TambahDosenActivity: BaseActivity<ActivityTambahDosenBinding>(){
    private lateinit var listDosen: MutableList<DataDosen>
    private lateinit var tambahDosenAdapter: TambahDosenAdapter

    companion object {
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, TambahDosenActivity::class.java)
            context.startActivity(starter)
        }
    }

    override fun getViewBinding(): ActivityTambahDosenBinding =
        ActivityTambahDosenBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()

        val backIcon = binding.icBackWhite
        backIcon.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
            onBackPressedDispatcher.addCallback(this) {
                finish()
            }
        }

        binding.rvTambahDosen.setOnClickListener{
            binding.icTambahDosen.isVisible = !binding.icTambahDosen.isVisible
        }

        binding.icTambahDosen.setOnClickListener {
            showOneActionDialog(
                message = getString(R.string.message_tambah_dosen),
                btnMessage = getString(R.string.btnMessage_tambah_dosen)
            )
        }

        listDosen = DataSourceKelasDosenMahasiswa().dataDosen()

        val tambahDosenAdapter =
            TambahDosenAdapter(this, listDosen)
        binding.rvTambahDosen.layoutManager = LinearLayoutManager(this)
        binding.rvTambahDosen.adapter = tambahDosenAdapter
    }
}