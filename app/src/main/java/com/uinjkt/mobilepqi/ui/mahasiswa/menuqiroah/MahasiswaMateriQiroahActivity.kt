package com.uinjkt.mobilepqi.ui.mahasiswa.menuqiroah

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.addCallback
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.domain.model.common.DataMateri
import com.mobilepqi.core.domain.model.menuqiroah.GetMateriQiroahModel
import com.uinjkt.mobilepqi.R
import com.uinjkt.mobilepqi.common.BaseActivity
import com.uinjkt.mobilepqi.databinding.ActivityMahasiswaMateriBinding
import com.uinjkt.mobilepqi.ui.mahasiswa.MenuMahasiswaMateriAdapterList
import org.koin.androidx.viewmodel.ext.android.viewModel


class MahasiswaMateriQiroahActivity : BaseActivity<ActivityMahasiswaMateriBinding>(), MenuMahasiswaMateriAdapterList.OnUserClickListener {

    companion object {
        @JvmStatic
        fun start(context: Context, idKelas: Int) {
            val starter = Intent(context, MahasiswaMateriQiroahActivity::class.java)
                .putExtra(ID_KELAS, idKelas)
            context.startActivity(starter)
        }
        private const val ID_KELAS = "idKelas"
    }

    private lateinit var listMateri: List<DataMateri>
    private val viewModel by viewModel<MahasiswaMateriQiroahViewModel>()
    private lateinit var mahasiswaMateriAdapter: MenuMahasiswaMateriAdapterList

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
        binding.tvTitleMenuMahasiswa.text = getString(R.string.tv_title_materi_qiroah)
        getMateriQiroah(intent.getIntExtra(ID_KELAS, 0))
    }

    private fun initObserver() {
        viewModel.getMateri.observe(this) { model ->
            when (model) {
                is Resource.Loading -> {
                    showLoading(true)
                }
                is Resource.Success -> {
                    showLoading(false)
                    model.data?.let {
                        actionAfterGetMateri(it)
                    }
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
        binding.tvEmptyState.isVisible = !value
    }

    private fun actionAfterGetMateri(model: GetMateriQiroahModel) {
        // Initialize data.
        listMateri = model.materi.map {
            DataMateri(
                id = it.id,
                title = it.title
            )
        }

        if(listMateri.isEmpty()) {
            showEmptyState(true)
        } else {
            showEmptyState(false)
            initAdapter()
        }

    }

    private fun showEmptyState(value: Boolean) {
        if(value) {
            binding.tvEmptyState.text = getString(R.string.empty_state, binding.tvTitleMenuMahasiswa.text.toString())
        }
        binding.tvEmptyState.isVisible = value
    }


    private fun initAdapter() {
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