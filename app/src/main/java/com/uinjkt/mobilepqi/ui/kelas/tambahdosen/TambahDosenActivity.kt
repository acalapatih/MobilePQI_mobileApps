package com.uinjkt.mobilepqi.ui.kelas.tambahdosen

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.addCallback
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.data.source.remote.response.tambahdosen.PostTambahDosenPayload
import com.mobilepqi.core.domain.model.tambahdosen.GetTambahDosenModel
import com.uinjkt.mobilepqi.R
import com.uinjkt.mobilepqi.common.BaseActivity
import com.uinjkt.mobilepqi.databinding.ActivityTambahDosenBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class TambahDosenActivity : BaseActivity<ActivityTambahDosenBinding>() {
    private var getListDosen: List<GetTambahDosenModel.GetTambahDosen> = listOf()
    private lateinit var tambahDosenAdapter: TambahDosenAdapter

    private val listDosenSelected: MutableList<GetTambahDosenModel.GetTambahDosen> = mutableListOf()

    companion object {
        @JvmStatic
        fun start(context: Context, idKelas: Int) {
            val starter = Intent(context, TambahDosenActivity::class.java).putExtra("idKelas", idKelas)
            context.startActivity(starter)
        }
    }

    private val viewModel by viewModel<TambahDosenViewModel>()

    private val classId by lazy { intent.getIntExtra("idKelas", 0) }

    override fun getViewBinding(): ActivityTambahDosenBinding =
        ActivityTambahDosenBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initListener()
        initObserver()
    }

    private fun initView() {
        getTambahDosen(classId)
    }

    private fun postTambahDosen(idKelas: Int, dosen: MutableList<GetTambahDosenModel.GetTambahDosen>) {
        viewModel.postTambahDosen(PostTambahDosenPayload(
            dosen = dosen.map {
                PostTambahDosenPayload.DosenItem(nim = it.nip)
            }
        ), idKelas)
    }

    private fun getTambahDosen(idKelas: Int) {
        viewModel.getTambahDosen(idKelas)
    }

    private fun initObserver() {
        viewModel.getTambahdosen.observe(this) { model ->
            when (model) {
                is Resource.Loading -> {
                    showLoading(true)
                }
                is Resource.Success -> {
                    showLoading(false)
                    getListDosen = model.data?.list ?: emptyList()
                    model.data?.let {
                        getListDosen = it.list
                        tambahDosenAdapter = TambahDosenAdapter(this, getListDosen, 2-it.dosenregistered)
                    }
                    binding.rvTambahDosen.layoutManager = LinearLayoutManager(this)
                    binding.rvTambahDosen.adapter = tambahDosenAdapter

                    tambahDosenAdapter.onDosenSelected = { data ->
                        if (listDosenSelected.contains(data)) {
                            listDosenSelected.remove(data)
                        } else {
                            listDosenSelected.add(data)
                        }
                        binding.icTambahDosen.isVisible = listDosenSelected.isNotEmpty()
                    }
                }
                is Resource.Error -> {
                    showLoading(false)
                    showToast(model.message ?: "Something Went Wrong")
                }
            }
        }
    }

    private fun initListener() {
        val backIcon = binding.icBackWhite
        backIcon.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
        onBackPressedDispatcher.addCallback(this) {
            finish()
        }

        binding.rvTambahDosen.setOnClickListener {
            binding.icTambahDosen.isVisible = !binding.icTambahDosen.isVisible
        }

        binding.icTambahDosen.setOnClickListener {
            postTambahDosen(classId, listDosenSelected)
            showOneActionDialogWithInvoke(
                message = getString(R.string.message_tambah_dosen),
                btnMessage = getString(R.string.btnMessage_tambah_dosen),
                onButtonClicked = { onBackPressedDispatcher.onBackPressed() }
            )
        }
    }

    private fun showLoading(value: Boolean) {
        binding.progressBar.isVisible = value
        binding.icTambahDosen.isVisible = !value
    }
}


