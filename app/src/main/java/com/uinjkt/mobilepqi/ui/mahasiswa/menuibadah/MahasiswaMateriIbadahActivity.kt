package com.uinjkt.mobilepqi.ui.mahasiswa.menuibadah

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.addCallback
import androidx.recyclerview.widget.LinearLayoutManager
import com.uinjkt.mobilepqi.R
import com.uinjkt.mobilepqi.ui.mahasiswa.MenuMahasiswaMateriAdapter
import com.uinjkt.mobilepqi.common.BaseActivity
import com.uinjkt.mobilepqi.data.DataMateri
import com.uinjkt.mobilepqi.data.DataSourceMateriIbadah
import com.uinjkt.mobilepqi.databinding.ActivityMahasiswaMateriBinding

class MahasiswaMateriIbadahActivity : BaseActivity<ActivityMahasiswaMateriBinding>() {
    
    private lateinit var listMateri: List<DataMateri> 
    
    companion object {
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, MahasiswaMateriIbadahActivity::class.java)
            context.startActivity(starter)
        }
    }

    override fun getViewBinding(): ActivityMahasiswaMateriBinding = ActivityMahasiswaMateriBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize data.
        listMateri = DataSourceMateriIbadah().loadDataMenuIbadah()

        // Initialize Adapter
        val adapter = MenuMahasiswaMateriAdapter(listMateri)
        binding.recycleViewMenuMahasiswa.layoutManager = LinearLayoutManager(this)
        binding.recycleViewMenuMahasiswa.adapter =  adapter

        // Initialize Title
        binding.tvTitleMenuMahasiswa.text = getString(R.string.tv_title_materi_ibadah)

        // adapterOnClickListener
        adapter.setOnItemClickListener(object : MenuMahasiswaMateriAdapter.onItemClickListener {
            override fun onItemClick(position: Int) {
                MahasiswaMateriDetailIbadahActivity.start(this@MahasiswaMateriIbadahActivity, listMateri[position].idMateri, listMateri[position].titleMenuName)
            }
        })

        // icon Close onClickListener
        binding.ivIconClose.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        onBackPressedDispatcher.addCallback(this) {
            finish()
        }
        
    }
    
    
}