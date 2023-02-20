package com.uinjkt.mobilepqi.ui.dosen.menutugas

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Context
import android.content.Intent
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.activity.addCallback
import androidx.annotation.RequiresApi
import com.uinjkt.mobilepqi.R
import com.uinjkt.mobilepqi.common.BaseActivity
import com.uinjkt.mobilepqi.databinding.ActivityDosenBuatTugasBaruBinding
import java.text.SimpleDateFormat
import java.util.*

class DosenBuatEditTugasActivity : BaseActivity<ActivityDosenBuatTugasBaruBinding>() {

    @RequiresApi(Build.VERSION_CODES.N)
    val myCalendar: Calendar = Calendar.getInstance()

    companion object {
        @JvmStatic
        fun start(context: Context, behavior: String = "buat") {
            val starter = Intent(context, DosenBuatEditTugasActivity::class.java)
                .putExtra(BEHAVIOR, behavior)
            context.startActivity(starter)
        }

        private const val BEHAVIOR = "behavior"
    }

    override fun getViewBinding(): ActivityDosenBuatTugasBaruBinding =
        ActivityDosenBuatTugasBaruBinding.inflate(layoutInflater)

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val behavior = intent.getStringExtra(BEHAVIOR)

        val listDataTopik =
            arrayOf("Praktikum Qiroah", "Praktikum Ibadah", "Hafalan Doa", "Hafalan Surah")
        val spinnerTopik = binding.spinnerTopikTugas
        val spinnerTopikArrayAdapter = ArrayAdapter(this, R.layout.item_spinner, listDataTopik)
        spinnerTopikArrayAdapter.setDropDownViewResource(R.layout.item_dropdown_spinner)
        spinnerTopik.adapter = spinnerTopikArrayAdapter

        val listDataJenisTugas = arrayOf("Ibadah", "Kelompok")
        val spinnerJenisTugas = binding.spinnerJenisTugas
        val spinnerJenisArrayAdapter = ArrayAdapter(this, R.layout.item_spinner, listDataJenisTugas)
        spinnerJenisArrayAdapter.setDropDownViewResource(R.layout.item_dropdown_spinner)
        spinnerJenisTugas.adapter = spinnerJenisArrayAdapter

        if (behavior == "edit") {
            with(binding) {
                tvTitleMenuBuatTugasDosen.text =
                    getString(R.string.tv_title_menu_edit_tugas_dosen)
                btnPostingTugasBaruDosen.visibility = View.GONE
                btnHapusTugas.visibility = View.VISIBLE
                btnSimpanEditTugas.visibility = View.VISIBLE
            }
        } else {
            with(binding) {
                tvTitleMenuBuatTugasDosen.text =
                    getString(R.string.tv_title_tugas_detail_mahasiswa)
                btnPostingTugasBaruDosen.visibility = View.VISIBLE
                btnHapusTugas.visibility = View.GONE
                btnSimpanEditTugas.visibility = View.GONE
            }
        }

        val textViewTglDeadline = binding.tvDeadlineTugas
        val date = OnDateSetListener { _, year, month, day ->
            myCalendar[Calendar.YEAR] = year
            myCalendar[Calendar.MONTH] = month
            myCalendar[Calendar.DAY_OF_MONTH] = day
            updatelabel()
        }

        textViewTglDeadline.setOnClickListener {
            with(binding.tvDeadlineTugas) {
                isEnabled = true
                inputType = 0
                setTextIsSelectable(true)
                isFocusable = false
            }
            val datePicker = DatePickerDialog(
                this@DosenBuatEditTugasActivity,
                date,
                myCalendar[Calendar.YEAR],
                myCalendar[Calendar.MONTH],
                myCalendar[Calendar.DAY_OF_MONTH]
            )
            datePicker.datePicker.minDate = System.currentTimeMillis()
            datePicker.show()
        }

        binding.btnPostingTugasBaruDosen.setOnClickListener {
            if (textViewTglDeadline.text.toString() == "Deadline") {
                showOneActionDialog("Tugas Gagal Diposting", "Okay")
            } else {
                showOneActionDialogWithInvoke("Tugas Berhasil Diposting", "Okay") {
                    onBackPressedDispatcher.onBackPressed()
                }
            }
        }

        binding.btnHapusTugas.setOnClickListener {
            showTwoActionDialog("Hapus Tugas", btnPositiveMessage = "Yes", btnNegativeMessage = "Batal") {
                showOneActionDialogWithInvoke("Tugas Berhasil Dihapus", "Okay") {
                    onBackPressedDispatcher.onBackPressed()
                }
            }
        }

        binding.btnSimpanEditTugas.setOnClickListener {
            showOneActionDialogWithInvoke("Perubahan Berhasil Disimpan","Okay") {
                onBackPressedDispatcher.onBackPressed()
            }
        }

        binding.ivLogoBackCircleButtonBuatTugas.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
        onBackPressedDispatcher.addCallback(this) {
            finish()
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun updatelabel() {
        val myFormat = "dd/MM/yyyy"
        val dateFormat = SimpleDateFormat(myFormat, Locale.US)
        val textView = binding.tvDeadlineTugas
        textView.text = dateFormat.format(myCalendar.time)
    }
}