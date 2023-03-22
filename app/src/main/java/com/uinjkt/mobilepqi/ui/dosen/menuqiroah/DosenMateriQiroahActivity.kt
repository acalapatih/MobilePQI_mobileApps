package com.uinjkt.mobilepqi.ui.dosen.menuqiroah

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.view.ViewGroup
import android.view.Window
import androidx.activity.addCallback
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.data.source.remote.response.qiroah.CreateMateriQiroahPayload
import com.mobilepqi.core.domain.model.common.DataMateri
import com.mobilepqi.core.domain.model.menuqiroah.GetMateriQiroahModel
import com.uinjkt.mobilepqi.R
import com.uinjkt.mobilepqi.common.BaseActivity
import com.uinjkt.mobilepqi.databinding.ActivityDosenMateriBinding
import com.uinjkt.mobilepqi.databinding.ItemDialogAddTopicActionBinding
import com.uinjkt.mobilepqi.ui.dosen.MenuDosenMateriAdapterList
import org.koin.androidx.viewmodel.ext.android.viewModel

class DosenMateriQiroahActivity : BaseActivity<ActivityDosenMateriBinding>(),
    MenuDosenMateriAdapterList.OnUserClickListener {

    companion object {
        @JvmStatic
        fun start(context: Context, idKelas: Int) {
            val starter = Intent(context, DosenMateriQiroahActivity::class.java)
                .putExtra(ID_KELAS, idKelas)
            context.startActivity(starter)
        }
        private const val ID_KELAS = "idKelas"
    }

    private lateinit var dosenMateriAdapter: MenuDosenMateriAdapterList
    private val viewModel by viewModel<DosenMateriQiroahViewModel>()
    private lateinit var dialogBinding: ItemDialogAddTopicActionBinding
    private lateinit var listMateri: List<DataMateri>
    private val idKelas by lazy { intent.getIntExtra(ID_KELAS, 0) }

    override fun getViewBinding(): ActivityDosenMateriBinding =
        ActivityDosenMateriBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initListener()
        initObserver()
    }

    private fun initObserver() {
        viewModel.createMateri.observe(this) { model ->
            when (model) {
                is Resource.Loading -> {
                    showLoadingInDialog(true)
                }
                is Resource.Success -> {
                    showLoadingInDialog(false)
                    showOneActionDialogWithInvoke("Materi Berhasil Ditambahkan", "Okay") {
                        model.data?.let { actionAfterCreateMateri() }
                    }
                }
                is Resource.Error -> {
                    showLoadingInDialog(false)
                    showOneActionDialogWithInvoke("Materi Gagal Ditambahkan", "Okay") {
                        showToast(model.message ?: "Something Went Wrong")
                    }
                }
            }
        }

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
                    showEmptyState(true)
                }
            }
        }

    }

    private fun showLoading(value: Boolean) {
        binding.pbLoadingScreen.isVisible = value
        binding.recycleViewMenuDosen.isVisible = !value
        binding.tvEmptyState.isVisible = !value
        binding.tvTapIcon.isVisible = !value
        binding.tvTapIconCont.isVisible = !value
    }

    private fun actionAfterGetMateri(model: GetMateriQiroahModel) {
        // Initialize Adapter & Layout
        listMateri = model.materi.map { materi ->
            DataMateri(
                id = materi.id,
                title = materi.title
            )
        }

        if(listMateri.isEmpty()) {
            showEmptyState(true)
        } else {
            showEmptyState(false)
        }
        initAdapter()
    }

    private fun showEmptyState(value: Boolean) {
        if(value) {
            val titleBar = binding.tvTitleMenuDosen.text.toString()
            binding.tvEmptyState.text = getString(R.string.empty_state, titleBar)
            binding.tvTapIconCont.text = getString(R.string.tap_icon_cont, titleBar)
        }
        binding.tvEmptyState.isVisible = value
        binding.tvTapIcon.isVisible = value
        binding.tvTapIconCont.isVisible = value
    }

    private fun initAdapter() {
        // Initialize Layout
        binding.recycleViewMenuDosen.layoutManager = LinearLayoutManager(this)
        dosenMateriAdapter = MenuDosenMateriAdapterList(this, listMateri, this)
        binding.recycleViewMenuDosen.adapter = dosenMateriAdapter
    }

    private fun actionAfterCreateMateri() {
        getMateriQiroah(idKelas)
    }

    private fun showLoadingInDialog(value: Boolean) {
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
        // Initialize Title
        binding.tvTitleMenuDosen.text = getString(R.string.tv_title_materi_qiroah)
        getMateriQiroah(idKelas)
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
                if (etInsertTopicText.text.isNotEmpty()) {
                    createMateriQiroah(etInsertTopicText.text.toString(), idKelas)
                    dialog.dismiss()
                } else {
                    dialog.hide()
                    showOneActionDialogWithInvoke("Materi Tidak Boleh Kosong", "Okay") {
                        dialog.show()
                    }
                }
            }

            tvBatalTambahkanMateriDosen.setOnClickListener {
                dialog.dismiss()
            }
        }

        dialog.show()
    }

    private fun createMateriQiroah(title: String, idKelas: Int) {
        viewModel.createMateriQiroah(
            CreateMateriQiroahPayload(title),idKelas
        )
    }

    private fun getMateriQiroah(idKelas: Int) {
        viewModel.getMateriQiroah(idKelas)
    }

    override fun onRestart() {
        super.onRestart()
        initView()
    }
}