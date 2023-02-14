package com.uinjkt.mobilepqi.ui.dosen.menuibadah

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.view.ViewGroup
import android.view.Window
import androidx.activity.addCallback
import androidx.recyclerview.widget.LinearLayoutManager
import com.uinjkt.mobilepqi.R
import com.uinjkt.mobilepqi.common.BaseActivity
import com.uinjkt.mobilepqi.data.DataMateri
import com.uinjkt.mobilepqi.data.DataSourceMateriIbadah
import com.uinjkt.mobilepqi.databinding.ActivityDosenMateriBinding
import com.uinjkt.mobilepqi.databinding.ItemDialogAddTopicActionBinding
import com.uinjkt.mobilepqi.ui.dosen.MenuDosenMateriAdapter

class DosenMateriIbadahActivity : BaseActivity<ActivityDosenMateriBinding>(), MenuDosenMateriAdapter.OnUserClickListener {

    private lateinit var listMateri: MutableList<DataMateri>
    private lateinit var dosenMateriAdapter: MenuDosenMateriAdapter

    companion object {
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, DosenMateriIbadahActivity::class.java)
            context.startActivity(starter)
        }
    }

    override fun getViewBinding(): ActivityDosenMateriBinding = ActivityDosenMateriBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize data.
        listMateri = DataSourceMateriIbadah().loadDataMenuIbadah()

        // Initialize Adapter & Layout
        dosenMateriAdapter = MenuDosenMateriAdapter(this, listMateri, this)
        binding.recycleViewMenuDosen.adapter = dosenMateriAdapter

        // Initialize Layout
        binding.recycleViewMenuDosen.layoutManager = LinearLayoutManager(this)

        // Initialize Title
        binding.tvTitleMenuDosen.text = getString(R.string.tv_title_materi_ibadah)

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


    override fun onUserClicked(position: Int) {
        DosenMateriDetailIbadahActivity.start(
            this@DosenMateriIbadahActivity,
            listMateri[position].idMateri,
            listMateri[position].titleMenuName
        )
    }

    private fun buttonTambahMateriClicked() {
        val dialog = Dialog(this)
        val dialogBinding = ItemDialogAddTopicActionBinding.inflate(layoutInflater)
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
                DataSourceMateriIbadah().addDataMenuIbadah(getText)
                dialog.dismiss()
                showOneActionThinFontDialog("Materi Berhasil Ditambahkan", "Okay")
            }

            tvBatalTambahkanMateriDosen.setOnClickListener {
                dialog.dismiss()
            }
        }

        dialog.show()
    }
}