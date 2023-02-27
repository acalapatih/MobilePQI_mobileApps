package com.uinjkt.mobilepqi.ui.dosen.menuqiroah

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.view.Window
import androidx.activity.addCallback
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.data.source.remote.response.menuqiroah.CreateMateriQiroahPayload
import com.mobilepqi.core.domain.model.menuqiroah.GetMateriQiroahModel
import com.uinjkt.mobilepqi.R
import com.uinjkt.mobilepqi.common.BaseActivity
import com.uinjkt.mobilepqi.data.DataSourceMateriQiroah
import com.uinjkt.mobilepqi.databinding.ActivityDosenMateriBinding
import com.uinjkt.mobilepqi.databinding.ItemDialogAddTopicActionBinding
import com.uinjkt.mobilepqi.ui.dosen.MenuDosenMateriAdapterList
import org.koin.androidx.viewmodel.ext.android.viewModel

class DosenMateriQiroahActivity : BaseActivity<ActivityDosenMateriBinding>(),
    MenuDosenMateriAdapterList.OnUserClickListener {
    private lateinit var dosenMateriAdapter: MenuDosenMateriAdapterList
    private val viewModel by viewModel<DosenMateriQiroahViewModel>()
    private lateinit var dialogBinding: ItemDialogAddTopicActionBinding
    private lateinit var listMateri: List<GetMateriQiroahModel.DataMateri>


    companion object {
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, DosenMateriQiroahActivity::class.java)
            context.startActivity(starter)
        }
    }

    override fun getViewBinding(): ActivityDosenMateriBinding =
        ActivityDosenMateriBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getMateriQiroah()
        initView()
        initListener()
        initObserver()
    }

    private fun initObserver() {
        viewModel.createMateri.observe(this) { model ->
            when (model) {
                is Resource.Loading -> {
                    showLoading(true)
                }
                is Resource.Success -> {
                    showLoading(false)
                    model.data?.let { actionAfterCreateMateri() }
                }
                is Resource.Error -> {
                    showLoading(false)
                    showToast(model.message ?: "")
                }
            }
        }

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

    private fun actionAfterGetMateri(getMateri: List<GetMateriQiroahModel.DataMateri>) {
        // Initialize Adapter & Layout
        listMateri = getMateri
        dosenMateriAdapter = MenuDosenMateriAdapterList(this, listMateri, this)
        binding.recycleViewMenuDosen.adapter = dosenMateriAdapter
    }

    private fun actionAfterCreateMateri() {
        showToast("Berhasil menambahkan Materi.")
    }

    private fun showLoading(value: Boolean) {
        dialogBinding.pbLoadingScreen.isVisible = value
        dialogBinding.btnTambahkanMateriDosen.isVisible = !value
        dialogBinding.tvBatalTambahkanMateriDosen.isVisible = !value
    }

    private fun initListener() {
        // icon Close onClickListener
        binding.ivIconClose.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding.tvIconAddTopic.setOnClickListener {
            buttonTambahMateriClicked()
        }

        onBackPressedDispatcher.addCallback(this) {
            finish()
        }
    }

    private fun initView() {
        // Initialize Layout
        binding.recycleViewMenuDosen.layoutManager = LinearLayoutManager(this)
        // Initialize Title
        binding.tvTitleMenuDosen.text = getString(R.string.tv_title_materi_qiroah)
    }

    override fun onUserClicked(position: Int) {
        DosenMateriDetailQiroahActivity.start(
            this@DosenMateriQiroahActivity, listMateri[position].id
        )
    }

    private fun buttonTambahMateriClicked() {
        val dialog = Dialog(this)
        dialogBinding = ItemDialogAddTopicActionBinding.inflate(layoutInflater)
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
            tvTitleMessage.text = getString(R.string.hint_et_insert_topic_materi)
            btnTambahkanMateriDosen.setOnClickListener {
                val getText = etInsertTopicText.text.toString()
                Log.d("TestPrint", getText)
                DataSourceMateriQiroah().addDataMenuQiroah(getText)
                if (etInsertTopicText.text.isNotEmpty()) {
                    showOneActionDialogWithInvoke("Materi Berhasil Ditambahkan", "Okay") {
                        createMateriQiroah(etInsertTopicText.text.toString())
                    }
                    dialog.dismiss()
                } else {
                    showOneActionDialog("Materi Tidak Boleh Kosong", "Okay")
                }
            }

            tvBatalTambahkanMateriDosen.setOnClickListener {
                dialog.dismiss()
            }
        }

        dialog.show()
    }

    private fun createMateriQiroah(title: String) {
        viewModel.createMateriQiroah(
            CreateMateriQiroahPayload(title = title)
        )
    }

    private fun getMateriQiroah() {
        viewModel.getMateriQiroah()
    }


}