package com.uinjkt.mobilepqi.ui.mahasiswa.menuqiroah

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.addCallback
import androidx.recyclerview.widget.LinearLayoutManager
import com.uinjkt.mobilepqi.R
import com.uinjkt.mobilepqi.common.BaseActivity
import com.uinjkt.mobilepqi.data.DataMateri
import com.uinjkt.mobilepqi.data.DataSourceMateriQiroah
import com.uinjkt.mobilepqi.databinding.ActivityMahasiswaMateriBinding
import com.uinjkt.mobilepqi.ui.mahasiswa.MenuMahasiswaMateriAdapter


class MahasiswaMateriQiroahActivity : BaseActivity<ActivityMahasiswaMateriBinding>(), MenuMahasiswaMateriAdapter.OnUserClickListener {

    private lateinit var listMateri: MutableList<DataMateri>
    private lateinit var mahasiswaMateriAdapter: MenuMahasiswaMateriAdapter

    companion object {
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, MahasiswaMateriQiroahActivity::class.java)
            context.startActivity(starter)
        }
    }

    override fun getViewBinding(): ActivityMahasiswaMateriBinding = ActivityMahasiswaMateriBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize data.
        listMateri = DataSourceMateriQiroah().loadDataMenuQiroah()

        // Initialize Adapter
        mahasiswaMateriAdapter = MenuMahasiswaMateriAdapter(this, listMateri, this)
        binding.recycleViewMenuMahasiswa.adapter =  mahasiswaMateriAdapter

        // Initialize Title
        binding.tvTitleMenuMahasiswa.text = getString(R.string.tv_title_materi_qiroah)

        binding.recycleViewMenuMahasiswa.layoutManager = LinearLayoutManager(this)


        // icon Close onClickListener
        binding.ivIconClose.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        onBackPressedDispatcher.addCallback(this) {
            finish()
        }
    }

    override fun onUserClicked(position: Int) {
        MahasiswaMateriDetailQiroahActivity.start(this@MahasiswaMateriQiroahActivity, listMateri[position].idMateri, listMateri[position].titleMenuName)
    }
}