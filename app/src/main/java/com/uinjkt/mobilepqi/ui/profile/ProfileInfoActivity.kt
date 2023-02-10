package com.uinjkt.mobilepqi.ui.profile

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Context
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import com.uinjkt.mobilepqi.common.BaseActivity
import com.uinjkt.mobilepqi.databinding.ActivityProfilInformasiBinding
import java.util.*


class ProfileInfoActivity: BaseActivity<ActivityProfilInformasiBinding>() {
    /**
     * Called when the activity is first created.
     */

    companion object{
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, ProfileInfoActivity::class.java)
            context.startActivity(starter)
        }
    }

    override fun getViewBinding(): ActivityProfilInformasiBinding = ActivityProfilInformasiBinding.inflate(layoutInflater)

    @RequiresApi(Build.VERSION_CODES.N)
    val myCalendar: Calendar = Calendar.getInstance()
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding.icBack.setOnClickListener { view ->
            val backIntent = Intent(view.context, ProfileActivity::class.java)
            startActivityForResult(backIntent, 0)
        }

        val editText_tglahir = binding.icEditTglahir
        val date =
            OnDateSetListener { _, year, month, day ->
                myCalendar[Calendar.YEAR] = year
                myCalendar[Calendar.MONTH] = month
                myCalendar[Calendar.DAY_OF_MONTH] = day
                updateLabel()
            }

        editText_tglahir.setOnClickListener {
            DatePickerDialog(
                this@ProfileInfoActivity, date,
                myCalendar[Calendar.YEAR],
                myCalendar[Calendar.MONTH], myCalendar[Calendar.DAY_OF_MONTH]
            ).show()
        }

        binding.btnSimpanProfil.setOnClickListener{
            val tgLahir = binding.inputTglahir.text.toString()
            Log.v("tgLahir", tgLahir)
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun updateLabel() {
        val myFormat = "dd/MM/yyyy"
        val dateFormat = SimpleDateFormat(myFormat, Locale.US)
        val editText = binding.inputTglahir
        editText.setText(dateFormat.format(myCalendar.time))
    }

}