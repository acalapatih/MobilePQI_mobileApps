package com.uinjkt.mobilepqi.ui.dosen.menutugas

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.addCallback
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.domain.model.tugas.GetListTugasMahasiswaModel
import com.uinjkt.mobilepqi.R
import com.uinjkt.mobilepqi.common.BaseActivity
import com.uinjkt.mobilepqi.databinding.ActivityDosenCekTugasMahasiswaBinding
import com.uinjkt.mobilepqi.ui.dosen.ListMahasiswaAdapterNew
import com.uinjkt.mobilepqi.util.capitalizeEachWord
import org.koin.androidx.viewmodel.ext.android.viewModel

class DosenCekTugasMahasiswaActivity : BaseActivity<ActivityDosenCekTugasMahasiswaBinding>(),
    ListMahasiswaAdapterNew.OnUserClickListener {

    companion object {
        @JvmStatic
        fun start(context: Context, idTugas: Int, topic: String, title: String) {
            val starter = Intent(context, DosenCekTugasMahasiswaActivity::class.java)
                .putExtra(ID_TUGAS, idTugas)
                .putExtra(TOPIC, topic)
                .putExtra(TITLE, title)
            context.startActivity(starter)
        }

        private const val ID_TUGAS = "idTugas"
        private const val TOPIC = "topik"
        private const val TITLE = "title"
    }

    private lateinit var listMahasiswaAdapter: ListMahasiswaAdapterNew

    private val idTugas by lazy { intent.getIntExtra(ID_TUGAS, 0) }
    private val titleTugas by lazy { intent.getStringExtra(TITLE) ?: "" }
    private val topikTugas by lazy { intent.getStringExtra(TOPIC) ?: "" }

    private val viewModel by viewModel<DosenCekTugasMahasiswaViewModel>()

    override fun getViewBinding(): ActivityDosenCekTugasMahasiswaBinding =
        ActivityDosenCekTugasMahasiswaBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initListener()
        initObserver()
    }

    private fun initView() {
        binding.tvTitleTugasDetailMahasiswa.text =
            getString(R.string.tv_title_tugas_detail_mahasiswa,
                "${topikTugas.capitalizeEachWord()} > ${titleTugas.capitalizeEachWord()}")
        getListTugasMahasiswa()
    }

    private fun getListTugasMahasiswa() {
        viewModel.getListTugasMahasiswa(idTugas)
    }

    private fun initListener() {
        binding.ivLogoBackCircleButtonTugasDosen.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
        onBackPressedDispatcher.addCallback(this) {
            finish()
        }
    }

    private fun initObserver() {
        viewModel.getListTugasMahasiswa.observe(this) { model ->
            when (model) {
                is Resource.Loading -> {
                    showloading(true)
                }
                is Resource.Success -> {
                    model.data?.let {
                        actionAfterGetListTugasMahasiswa(it)
                    }
                    showloading(false)
                }
                is Resource.Error -> {
                    showToast(model.message ?: "Something Went Wrong")
                    showloading(false)
                }
            }

        }
    }

    private fun actionAfterGetListTugasMahasiswa(model: GetListTugasMahasiswaModel) {
        // Initialize Adapter
        listMahasiswaAdapter = ListMahasiswaAdapterNew(this,
            model.jawaban ?: emptyList(), this)
        binding.rvListMahasiswa.adapter = listMahasiswaAdapter
        // Set Layout Manager
        binding.rvListMahasiswa.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        binding.tvJudulTugasDosen.text = titleTugas
    }

    private fun showloading(value: Boolean) {
        binding.pbLoadingScreen.isVisible = value
        binding.nsvContentDetailListMahasiswa.isVisible = !value
    }

    override fun onUserClickListener(nim: String, status: Boolean) {
        if (status) {
            DosenBeriNilaiTugasMahasiswaActivity.start(this@DosenCekTugasMahasiswaActivity,
                idTugas,
                nim)
        } else {
            showOneActionThinFontDialog("Mahasiswa belum mengumpulkan Tugas", "Okay")
        }
    }

    override fun onRestart() {
        super.onRestart()
        getListTugasMahasiswa()
    }
}