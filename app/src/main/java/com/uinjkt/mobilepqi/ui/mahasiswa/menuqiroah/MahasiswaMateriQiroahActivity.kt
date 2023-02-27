package com.uinjkt.mobilepqi.ui.mahasiswa.menuqiroah

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.addCallback
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.domain.model.menuqiroah.GetMateriQiroahModel
import com.uinjkt.mobilepqi.R
import com.uinjkt.mobilepqi.common.BaseActivity
import com.uinjkt.mobilepqi.databinding.ActivityMahasiswaMateriBinding
import com.uinjkt.mobilepqi.ui.mahasiswa.MenuMahasiswaMateriAdapterList
import org.koin.androidx.viewmodel.ext.android.viewModel


class MahasiswaMateriQiroahActivity : BaseActivity<ActivityMahasiswaMateriBinding>(), MenuMahasiswaMateriAdapterList.OnUserClickListener {

    private lateinit var listMateri: List<GetMateriQiroahModel.DataMateri>
    private val viewModel by viewModel<MahasiswaMateriQiroahViewModel>()
    private lateinit var mahasiswaMateriAdapter: MenuMahasiswaMateriAdapterList

    companion object {
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, MahasiswaMateriQiroahActivity::class.java)
            context.startActivity(starter)
        }
    }

    override fun getViewBinding(): ActivityMahasiswaMateriBinding = ActivityMahasiswaMateriBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getMateriQiroah()
        initView()
        initListener()
        initObserver()
    }

    private fun initListener() {
        // icon Close onClickListener
        binding.ivIconClose.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        onBackPressedDispatcher.addCallback(this) {
            finish()
        }
    }

    private fun initView() {
        // Initialize Title
        binding.tvTitleMenuMahasiswa.text = getString(R.string.tv_title_materi_qiroah)
        binding.recycleViewMenuMahasiswa.layoutManager = LinearLayoutManager(this)
    }

    private fun initObserver() {
        viewModel.getMateri.observe(this) { model ->
            when (model) {
                is Resource.Loading -> {
                    showToast("loading")
                }
                is Resource.Success -> {
                    model.data?.let {
                        actionAfterGetMateri(it.materi)
                    }
                }
                is Resource.Error -> {
                    showToast(model.message ?: "")
                }
            }
        }

    }

    private fun actionAfterGetMateri(materi: List<GetMateriQiroahModel.DataMateri>) {
        // Initialize data.
        listMateri = materi

        // Initialize Adapter
        mahasiswaMateriAdapter = MenuMahasiswaMateriAdapterList(this, listMateri, this)
        binding.recycleViewMenuMahasiswa.adapter =  mahasiswaMateriAdapter
    }

    private fun getMateriQiroah() {
        viewModel.getMateriQiroah()
    }

    override fun onUserClicked(position: Int) {
        MahasiswaMateriDetailQiroahActivity.start(this@MahasiswaMateriQiroahActivity, listMateri[position].id)
    }
}