package com.uinjkt.mobilepqi.ui.kelas.daftarkelas

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.domain.model.daftarkelas.DaftarKelasModel
import com.uinjkt.mobilepqi.common.BaseActivity
import com.uinjkt.mobilepqi.databinding.ActivityDaftarKelasBinding
import com.uinjkt.mobilepqi.ui.kelas.buatkelas.BuatKelasActivity
import com.uinjkt.mobilepqi.ui.kelas.detailkelas.DetailKelasActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class DaftarKelasActivity : BaseActivity<ActivityDaftarKelasBinding>(),
    DaftarKelasAdapter.OnUserClickListener {
    private var listKelas: List<DaftarKelasModel.DaftarKelas> = listOf()

    companion object {
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, DaftarKelasActivity::class.java)
            context.startActivity(starter)
        }
    }

    private val viewModel by viewModel<DaftarKelasViewModel>()

    private val createClassLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == AppCompatActivity.RESULT_OK) {
            viewModel.daftarkelas()
        }
    }

    override fun getViewBinding(): ActivityDaftarKelasBinding =
        ActivityDaftarKelasBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initListener()
        initObserver()
        viewModel.daftarkelas()
    }

    private fun initObserver() {
        viewModel.daftarkelas.observe(this) { model ->
            when (model) {
                is Resource.Loading -> {
                    showLoading(true)
                }
                is Resource.Success -> {
                    showLoading(false)
                    listKelas = model.data?.list ?: emptyList()
                    val daftarKelasAdapter =
                        DaftarKelasAdapter(this, listKelas, this)
                    binding.rvDaftarKelas.layoutManager = LinearLayoutManager(this)
                    binding.rvDaftarKelas.adapter = daftarKelasAdapter
                }
                is Resource.Error -> {
                    showLoading(false)
                    showToast(model.message ?: "")
                }
            }
        }
    }

    private fun showLoading(value: Boolean) {
        binding.progressBar.isVisible = value
    }

    private fun initListener() {
        binding.tvBuatkelas.setOnClickListener {
            createClassLauncher.launch(BuatKelasActivity.start(this))
        }
    }

    override fun onUserClicked(position: Int, clicked: String) {
        if (clicked == "anggota") {
            DetailKelasActivity.start(this)
        } else {
            BuatKelasActivity.start(this)
        }
    }
}
