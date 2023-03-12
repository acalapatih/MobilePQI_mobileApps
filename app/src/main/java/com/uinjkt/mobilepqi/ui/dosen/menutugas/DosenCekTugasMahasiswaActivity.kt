package com.uinjkt.mobilepqi.ui.dosen.menutugas

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.addCallback
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobilepqi.core.data.Resource
import com.mobilepqi.core.domain.model.tugas.GetListTugasMahasiswaModel
import com.uinjkt.mobilepqi.common.BaseActivity
import com.uinjkt.mobilepqi.databinding.ActivityDosenCekTugasMahasiswaBinding
import com.uinjkt.mobilepqi.ui.dosen.ListMahasiswaAdapterNew
import org.koin.androidx.viewmodel.ext.android.viewModel

class DosenCekTugasMahasiswaActivity : BaseActivity<ActivityDosenCekTugasMahasiswaBinding>(),
    ListMahasiswaAdapterNew.OnUserClickListener {

    companion object {
        @JvmStatic
        fun start(context: Context, idTugas: Int) {
            val starter = Intent(context, DosenCekTugasMahasiswaActivity::class.java)
                .putExtra(ID_TUGAS, idTugas)
            context.startActivity(starter)
        }

        private const val ID_TUGAS = "idTugas"
    }

    private lateinit var listMahasiswaAdapter: ListMahasiswaAdapterNew

    private val idTugas by lazy { intent.getIntExtra(ID_TUGAS, 0) }

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
    }

    private fun showloading(value: Boolean) {
        binding.pbLoadingScreen.isVisible = value
        binding.nsvContentDetailListMahasiswa.isVisible = !value
    }

    override fun onUserClickListener(position: Int) {
        DosenBeriNilaiTugasMahasiswaActivity.start(this@DosenCekTugasMahasiswaActivity, position)
    }
}