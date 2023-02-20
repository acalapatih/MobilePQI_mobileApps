package com.uinjkt.mobilepqi.ui.mahasiswa.menutugas

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
import com.uinjkt.mobilepqi.databinding.ActivityMahasiswaTugasFilteredBinding
import com.uinjkt.mobilepqi.ui.mahasiswa.ListMahasiswaTugasAdapter
import com.uinjkt.mobilepqi.ui.mahasiswa.MenuMahasiswaJenisTugasAdapter


class MahasiswaTugasFilterActivity : BaseActivity<ActivityMahasiswaTugasFilteredBinding>(), ListMahasiswaTugasAdapter.OnUserClickTugasListener, MenuMahasiswaJenisTugasAdapter.OnUserClickJenisTugasListener {
    private lateinit var listJenisTugas: MutableList<DataJenisTugas>
    private lateinit var listTugas: MutableList<DataTugas>
    private lateinit var mahasiswaJenisTugasAdapter: MenuMahasiswaJenisTugasAdapter
    private lateinit var mahasiswaTugasFilterAdapter: ListMahasiswaTugasAdapter

    companion object {
        @JvmStatic
        fun start(context: Context, idPosition: Int) {
            val starter = Intent(context, MahasiswaTugasFilterActivity::class.java)
                .putExtra(ID, idPosition)
            context.startActivity(starter)
        }

        private const val ID = "id"
    }

    override fun getViewBinding(): ActivityMahasiswaTugasFilteredBinding = ActivityMahasiswaTugasFilteredBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Initialize data.
        listJenisTugas = DataSourceJenisTugas().getDataJenisTugas(intent.getIntExtra(ID,2))
        listTugas = DataSourceTugas().dataTugas

        // Initialize Adapter Jenis Tugas
        mahasiswaJenisTugasAdapter = MenuMahasiswaJenisTugasAdapter(this, listJenisTugas, this)
        binding.rvJenisTugasMahasiswaFilter.adapter = mahasiswaJenisTugasAdapter

        // Initialize Adapter Tugas Filter
        mahasiswaTugasFilterAdapter = ListMahasiswaTugasAdapter(this, listTugas, this)
        binding.rvListTugasMahasiswaFilter.adapter = mahasiswaTugasFilterAdapter

        // Set Layout Manager
        binding.rvJenisTugasMahasiswaFilter.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvListTugasMahasiswaFilter.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        binding.ivLogoBackCircleButtonTugasMahasiswaFiltered.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        onBackPressedDispatcher.addCallback(this) {
            finish()
        }
    }

    override fun onUserTugasClicked(position: Int) {
        MahasiswaDetailTugasActivity.start(this@MahasiswaTugasFilterActivity)
    }

    override fun onUserJenisTugasClicked(data: DataJenisTugas) {
        binding.tvTugasFiltered.text = data.titleJenisTugas
    }

}