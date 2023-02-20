package com.uinjkt.mobilepqi.ui.dosen.menutugas

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.addCallback
import androidx.recyclerview.widget.LinearLayoutManager
import com.uinjkt.mobilepqi.common.BaseActivity
import com.uinjkt.mobilepqi.data.DataJenisTugas
import com.uinjkt.mobilepqi.data.DataSourceJenisTugas
import com.uinjkt.mobilepqi.data.DataSourceTugas
import com.uinjkt.mobilepqi.data.DataTugas
import com.uinjkt.mobilepqi.databinding.ActivityDosenTugasFilteredBinding
import com.uinjkt.mobilepqi.ui.mahasiswa.ListMahasiswaTugasAdapter
import com.uinjkt.mobilepqi.ui.mahasiswa.MenuMahasiswaJenisTugasAdapter

class DosenTugasFilterActivity : BaseActivity<ActivityDosenTugasFilteredBinding>(), ListMahasiswaTugasAdapter.OnUserClickTugasListener, MenuMahasiswaJenisTugasAdapter.OnUserClickJenisTugasListener {

    private lateinit var listJenisTugas: MutableList<DataJenisTugas>
    private lateinit var listTugas: MutableList<DataTugas>
    private lateinit var dosenJenisTugasAdapter: MenuMahasiswaJenisTugasAdapter
    private lateinit var dosenTugasFilterAdapter: ListMahasiswaTugasAdapter

    companion object {
        @JvmStatic
        fun start(context: Context, idPosition: Int) {
            val starter = Intent(context, DosenTugasFilterActivity::class.java)
                .putExtra(ID, idPosition)
            context.startActivity(starter)
        }

        private const val ID = "id"
    }
    override fun getViewBinding(): ActivityDosenTugasFilteredBinding = ActivityDosenTugasFilteredBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Initialize data.
        listJenisTugas = DataSourceJenisTugas().getDataJenisTugas(intent.getIntExtra(ID,2))
        listTugas = DataSourceTugas().dataTugas

        // Initialize Adapter Jenis Tugas
        dosenJenisTugasAdapter = MenuMahasiswaJenisTugasAdapter(this, listJenisTugas, this)
        binding.rvJenisTugasDosenFilter.adapter = dosenJenisTugasAdapter

        // Initialize Adapter Tugas Filter
        dosenTugasFilterAdapter = ListMahasiswaTugasAdapter(this, listTugas, this)
        binding.rvListTugasDosenFilter.adapter = dosenTugasFilterAdapter

        // Set Layout Manager
        binding.rvJenisTugasDosenFilter.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvListTugasDosenFilter.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        binding.ivLogoBackCircleButtonTugasDosenFilter.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding.tvIconUnduhNilai.setOnClickListener {
            showTwoActionDialog("Unduh Nilai?", btnPositiveMessage = "Unduh", btnNegativeMessage = "batal") {
                showOneActionDialog("Nilai Berhasil Diunduh", "Okay")
            }
        }

        onBackPressedDispatcher.addCallback(this) {
            finish()
        }
    }

    override fun onUserTugasClicked(position: Int) {
        DosenDetailTugasActivity.start(this@DosenTugasFilterActivity)
    }

    override fun onUserJenisTugasClicked(data: DataJenisTugas) {
        binding.tvTugasFilter.text = data.titleJenisTugas
    }
}