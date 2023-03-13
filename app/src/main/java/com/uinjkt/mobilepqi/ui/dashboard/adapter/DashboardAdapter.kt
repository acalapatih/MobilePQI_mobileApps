package com.uinjkt.mobilepqi.ui.dashboard.adapter

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.mobilepqi.core.domain.model.dashboard.GetTugasModel
import com.uinjkt.mobilepqi.R
import com.uinjkt.mobilepqi.databinding.RecycleViewTugasDashboardBinding
import com.uinjkt.mobilepqi.util.convertTime
import java.util.*
import java.util.Calendar as JavaCalendar

class DashboardAdapter(
    private val context: Context,
    private val dataset: List<GetTugasModel.ListTugas>
) : RecyclerView.Adapter<DashboardAdapter.ViewHolder>() {
    @RequiresApi(Build.VERSION_CODES.N)
    val myCalendar: android.icu.util.Calendar = android.icu.util.Calendar.getInstance()
    val myCalendarJava: JavaCalendar = JavaCalendar.getInstance()

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = RecycleViewTugasDashboardBinding.bind(view)
        fun bindItem(tugasDashboard: GetTugasModel.ListTugas) {
            binding.tvNamaTugas.text = tugasDashboard.title

//            val myFormat = "dd MMM yyyy, hh:mm Z"
//            val dateFormatTglTugas = SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSSSSSS Z", Locale.US)
//            val dateFormatDlTugas = SimpleDateFormat("yyyy-MM-dd hh:mm:ss Z", Locale.US)
//            val newDateFormat = SimpleDateFormat(myFormat, Locale.US)
//            if (Build.VERSION.SDK_INT >= 24) {
//                dateFormatTglTugas.format(myCalendar.time)
//                dateFormatDlTugas.format(myCalendar.time)
//                newDateFormat.format(myCalendar.time)
//            } else {
//                dateFormatTglTugas.format(myCalendarJava.time)
//                dateFormatDlTugas.format(myCalendarJava.time)
//                newDateFormat.format(myCalendarJava.time)
//            }
//
//            val tglTugas = dateFormatTglTugas.parse(tugasDashboard.createdAt)
//            val newTglTugas = tglTugas?.let { newDateFormat.format(it) }
//
//            val dlTugas = dateFormatDlTugas.parse(tugasDashboard.deadline)
//            val newDlTugas = dlTugas?.let { newDateFormat.format(it) }
            binding.tvDeadlineTugas.text = tugasDashboard.deadline.convertTime("dd MMM yyyy, hh:mm Z")
            binding.tvTanggalTugas.text = tugasDashboard.createdAt.convertTime("dd MMM yyyy, hh:mm Z")
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.recycle_view_tugas_dashboard,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = dataset.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(dataset[position])
    }
}