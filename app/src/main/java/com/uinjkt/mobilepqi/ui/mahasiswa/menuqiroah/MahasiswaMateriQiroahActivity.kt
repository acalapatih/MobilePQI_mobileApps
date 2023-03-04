package com.uinjkt.mobilepqi.ui.mahasiswa.menuqiroah

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.addCallback
import androidx.core.view.isVisible
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
        fun start(context: Context, idKelas: Int) {
            val starter = Intent(context, MahasiswaMateriQiroahActivity::class.java)
                .putExtra(ID_KELAS, idKelas)
            context.startActivity(starter)
        }
        private const val ID_KELAS = "idKelas"
    }

    override fun getViewBinding(): ActivityMahasiswaMateriBinding = ActivityMahasiswaMateriBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
        getMateriQiroah(intent.getIntExtra(ID_KELAS, 0))
        binding.tvTitleMenuMahasiswa.text = getString(R.string.tv_title_materi_qiroah)
    }

    private fun initObserver() {
        viewModel.getMateri.observe(this) { model ->
            when (model) {
                is Resource.Loading -> {
                    showLoading(true)
                }
                is Resource.Success -> {
                    model.data?.let {
                        actionAfterGetMateri(it.materi)
                    }
                    showLoading(false)
                }
                is Resource.Error -> {
                    showToast(model.message ?: "Something Went Wrong")
                    showLoading(false)
                }
            }
        }

    }

    private fun showLoading(value: Boolean) {
        binding.pbLoadingScreen.isVisible = value
        binding.recycleViewMenuMahasiswa.isVisible = !value
    }

    private fun actionAfterGetMateri(materi: List<GetMateriQiroahModel.DataMateri>) {
        // Initialize data.
        listMateri = materi

        // Initialize Adapter
        mahasiswaMateriAdapter = MenuMahasiswaMateriAdapterList(this, listMateri, this)
        binding.recycleViewMenuMahasiswa.adapter =  mahasiswaMateriAdapter
        binding.recycleViewMenuMahasiswa.layoutManager = LinearLayoutManager(this)
    }

    private fun getMateriQiroah(idKelas: Int) {
        viewModel.getMateriQiroah(idKelas)
    }

    override fun onRestart() {
        super.onRestart()
        initView()
    }

    override fun onUserClicked(position: Int) {
        MahasiswaMateriDetailQiroahActivity.start(this@MahasiswaMateriQiroahActivity, listMateri[position].id)
    }
}