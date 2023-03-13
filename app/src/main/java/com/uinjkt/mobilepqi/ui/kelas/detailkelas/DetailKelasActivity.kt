package com.uinjkt.mobilepqi.ui.kelas.detailkelas

import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.addCallback
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.domain.model.detailkelas.DetailKelasModel
import com.uinjkt.mobilepqi.common.BaseActivity
import com.uinjkt.mobilepqi.databinding.ActivityDetailKelasBinding
import com.uinjkt.mobilepqi.ui.kelas.adapter.DosenAdapter
import com.uinjkt.mobilepqi.ui.kelas.adapter.MahasiswaAdapter
import com.uinjkt.mobilepqi.ui.kelas.tambahdosen.TambahDosenActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailKelasActivity : BaseActivity<ActivityDetailKelasBinding>() {
    private var listDosen: List<DetailKelasModel.ListDosen> = listOf()
    private var listMahasiswa: List<DetailKelasModel.ListMahasiswa> = listOf()

    private lateinit var dosenAdapter: DosenAdapter
    private lateinit var mahasiswaAdapter: MahasiswaAdapter

    companion object {
        @JvmStatic
        fun start(context: Context, idKelas: Int): Intent {
            return Intent(context, DetailKelasActivity::class.java).putExtra("idKelas", idKelas)
        }
    }

    private val viewModel by viewModel<DetailKelasViewModel>()

    private val classId by lazy { intent.getIntExtra("idKelas", 0) }

    override fun getViewBinding(): ActivityDetailKelasBinding =
        ActivityDetailKelasBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initListener()
        initObserver()
    }

    private fun initView() {
        getDetailKelas(classId)
    }

    private fun getDetailKelas(idKelas: Int) {
        viewModel.detailkelas(idKelas)
    }

    private fun initObserver() {
        viewModel.detailkelas.observe(this) { model ->
            when (model) {
                is Resource.Loading -> {
                    showLoading(true)
                }
                is Resource.Success -> {
                    showLoading(false)
                    binding.tvKodeKelas.text = model.data?.code

                    listDosen = model.data?.listdosen ?: emptyList()
                    dosenAdapter = DosenAdapter(this, listDosen)
                    binding.rvProfilDosen.layoutManager = LinearLayoutManager(this)
                    binding.rvProfilDosen.adapter = dosenAdapter

                    listMahasiswa = model.data?.listmahasiswa ?: emptyList()
                    mahasiswaAdapter = MahasiswaAdapter(this, listMahasiswa)
                    binding.rvProfilMahasiswa.layoutManager = LinearLayoutManager(this)
                    binding.rvProfilMahasiswa.adapter = mahasiswaAdapter

                    if (listDosen.size < 2) {
                        binding.tvLabelTambahDosen.isEnabled = true
                    } else {
                        binding.tvLabelTambahDosen.isEnabled = false
                        binding.tvLabelTambahDosen.alpha = 0.5F
                    }

                    setResult(Activity.RESULT_OK)
                }
                is Resource.Error -> {
                    showLoading(false)
                    showToast(model.message ?: "Something when wrong")
                }
            }
        }
    }

    private fun showLoading(value: Boolean) {
        binding.progressBar.isVisible = value
    }

    private fun initListener() {
        binding.icBackWhite.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
        onBackPressedDispatcher.addCallback(this) {
            finish()
        }

        binding.icCopyKodeKelas.setOnClickListener {
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("Kode Kelas", binding.tvKodeKelas.text)
            clipboard.setPrimaryClip(clip)

            clip.description

            showToast("Copied")
        }

        binding.tvLabelTambahDosen.setOnClickListener {
            TambahDosenActivity.start(this, classId)
        }
    }

    override fun onRestart() {
        super.onRestart()
        initView()
    }
}