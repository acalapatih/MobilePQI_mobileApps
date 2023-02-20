package com.uinjkt.mobilepqi.ui.dosen.menutugas

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.addCallback
import androidx.recyclerview.widget.LinearLayoutManager
import com.uinjkt.mobilepqi.common.BaseActivity
import com.uinjkt.mobilepqi.data.DataSourceTugas
import com.uinjkt.mobilepqi.data.DataTugasMahasiswa
import com.uinjkt.mobilepqi.databinding.ActivityDosenCekTugasMahasiswaBinding
import com.uinjkt.mobilepqi.ui.dosen.ListMahasiswaAdapter

class DosenCekTugasMahasiswaActivity : BaseActivity<ActivityDosenCekTugasMahasiswaBinding>() , ListMahasiswaAdapter.OnUserClickListener{

    private lateinit var listMahasiswa: MutableList<DataTugasMahasiswa>
    private lateinit var listMahasiswaAdapter: ListMahasiswaAdapter
    companion object {
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, DosenCekTugasMahasiswaActivity::class.java)
            context.startActivity(starter)
        }
    }
    override fun getViewBinding(): ActivityDosenCekTugasMahasiswaBinding = ActivityDosenCekTugasMahasiswaBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize data.
        listMahasiswa = DataSourceTugas().dataMahasiswa

        // Initialize Adapter
        listMahasiswaAdapter = ListMahasiswaAdapter(this,listMahasiswa,this)
        binding.rvListMahasiswa.adapter = listMahasiswaAdapter

        // Set Layout Manager
        binding.rvListMahasiswa.layoutManager= LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        binding.ivLogoBackCircleButtonTugasDosen.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
        onBackPressedDispatcher.addCallback(this) {
            finish()
        }


    }

    override fun onUserClickListener(position: Int) {
        DosenBeriNilaiTugasMahasiswaActivity.start(this@DosenCekTugasMahasiswaActivity, position)
    }
}