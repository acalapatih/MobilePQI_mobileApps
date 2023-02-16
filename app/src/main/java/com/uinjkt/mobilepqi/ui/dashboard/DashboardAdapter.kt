package com.uinjkt.mobilepqi.ui.dashboard

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.uinjkt.mobilepqi.R
import com.uinjkt.mobilepqi.data.DataTugasDashboard
import com.uinjkt.mobilepqi.databinding.RecycleViewTugasDashboardBinding

class DashboardAdapter(
    private val context: Context,
    private val dataset: MutableList<DataTugasDashboard>
) : RecyclerView.Adapter<DashboardAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = RecycleViewTugasDashboardBinding.bind(view)

        fun bindItem(tugasDashboard: DataTugasDashboard) {
            binding.tvTanggalTugas.text = tugasDashboard.tanggalTugas
            binding.tvNamaTugas.text = tugasDashboard.namaTugas
            binding.tvDeadlineTugas.text = tugasDashboard.deadlineTugas
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