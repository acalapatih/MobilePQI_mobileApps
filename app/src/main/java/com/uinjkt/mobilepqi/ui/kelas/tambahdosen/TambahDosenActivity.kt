package com.uinjkt.mobilepqi.ui.kelas.tambahdosen

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.addCallback
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.jakewharton.rxbinding2.widget.RxTextView
import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.data.source.remote.response.tambahdosen.PostTambahDosenPayload
import com.mobilepqi.core.domain.model.tambahdosen.GetTambahDosenModel
import com.uinjkt.mobilepqi.R
import com.uinjkt.mobilepqi.common.BaseActivity
import com.uinjkt.mobilepqi.databinding.ActivityTambahDosenBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class TambahDosenActivity : BaseActivity<ActivityTambahDosenBinding>() {
    private var getListDosen: MutableList<GetTambahDosenModel.GetTambahDosen> = mutableListOf()
    private lateinit var tambahDosenAdapter: TambahDosenAdapter

    private val listDosenSelected: MutableList<GetTambahDosenModel.GetTambahDosen> = mutableListOf()

    companion object {
        @JvmStatic
        fun start(context: Context, idKelas: Int) {
            val starter = Intent(context, TambahDosenActivity::class.java)
                .putExtra(ID_KELAS, idKelas)
            context.startActivity(starter)
        }
        private const val ID_KELAS = "id_kelas"
    }

    private val viewModel by viewModel<TambahDosenViewModel>()
    private val classId by lazy { intent.getIntExtra(ID_KELAS, 0) }

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

    private fun postTambahDosen(
        idKelas: Int,
        dosen: MutableList<GetTambahDosenModel.GetTambahDosen>
    ) {
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
                    getListDosen = model.data?.list?.toMutableList() ?: mutableListOf()
                    model.data?.let {
                        getListDosen = it.list.toMutableList()
                        tambahDosenAdapter =
                            TambahDosenAdapter(this, getListDosen, 2 - it.dosenregistered)
                    }
                    binding.rvTambahDosen.layoutManager = LinearLayoutManager(this)
                    binding.rvTambahDosen.adapter = tambahDosenAdapter

                    binding.icTambahDosen.isVisible = listDosenSelected.isNotEmpty()

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
        viewModel.postTambahdosen.observe(this) { model ->
            when (model) {
                is Resource.Loading -> {
                    showLoading(true)
                }
                is Resource.Success -> {
                    showLoading(false)
                    showOneActionDialogWithInvoke(
                        message = getString(R.string.message_tambah_dosen),
                        btnMessage = getString(R.string.btnMessage_tambah_dosen),
                        onButtonClicked = { onBackPressedDispatcher.onBackPressed() }
                    )
                }
                is Resource.Error -> {
                    showLoading(false)
                    showToast(model.message ?: "Something Went Wrong")
                }
            }
        }

        viewModel.postTambahdosen.observe(this) { model ->
            when (model) {
                is Resource.Loading -> {
                    showLoading(true)
                }
                is Resource.Success -> {
                    showLoading(false)
                    showOneActionDialogWithInvoke(
                        message = getString(R.string.message_tambah_dosen),
                        btnMessage = getString(R.string.btnMessage_tambah_dosen),
                        onButtonClicked = { onBackPressedDispatcher.onBackPressed() }
                    )
                }
                is Resource.Error -> {
                    showLoading(false)
                    showToast(model.message ?: "Something Went Wrong")
                }
            }
        }
    }

    @SuppressLint("CheckResult")
    private fun initListener() {
        binding.icBackWhite.setOnClickListener {
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
            viewModel.postTambahdosen
        }

        val searchDosenStream = RxTextView.textChanges(binding.etSearchDosen)
            .skipInitialValue()
            .map { charSequence ->
                charSequence.isNotEmpty()
            }
        searchDosenStream.subscribe{ isValid ->
            if (isValid) {
                binding.icSearchDosen.alpha = 1F
                binding.icSearchDosen.isClickable = isValid
                filter(binding.etSearchDosen.text.toString())
            } else {
                binding.icSearchDosen.alpha = 0.5F
                binding.icSearchDosen.isClickable = isValid
                tambahDosenAdapter.filterList(getListDosen)
            }
        }

        binding.icSearchDosen.setOnClickListener {
            filter(binding.etSearchDosen.text.toString())
        }
    }

    private fun filter(text: String) {
        val newText = text.lowercase()
        val filteredList: MutableList<GetTambahDosenModel.GetTambahDosen> = mutableListOf()
        for (model in getListDosen) {
            val name = model.name.lowercase()
            val nim = model.nip.lowercase()
            if (name.contains(newText)) {
                filteredList.add(model)
            } else if (nim.contains(newText)) {
                filteredList.add(model)
            }
        }
        tambahDosenAdapter.filterList(filteredList)
    }

    private fun showLoading(value: Boolean) {
        binding.progressBar.isVisible = value
        binding.icTambahDosen.isVisible = !value
    }
}


