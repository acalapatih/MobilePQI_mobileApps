package com.uinjkt.mobilepqi.ui.dashboard.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mobilepqi.core.domain.model.dashboard.GetTugasModel
import com.uinjkt.mobilepqi.R
import com.uinjkt.mobilepqi.databinding.RecycleViewTugasDashboardBinding
import com.uinjkt.mobilepqi.util.convertTime

class DashboardAdapter(
    private val context: Context,
    private val dataset: List<GetTugasModel.ListTugas>
) : RecyclerView.Adapter<DashboardAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = RecycleViewTugasDashboardBinding.bind(view)
        fun bindItem(tugasDashboard: GetTugasModel.ListTugas) {
            binding.tvNamaTugas.text = tugasDashboard.title
            binding.tvDeadlineTugas.text = tugasDashboard.deadline.convertTime("dd MMM yyyy (HH.mm)")
            binding.tvTanggalTugas.text = tugasDashboard.createdAt.convertTime("dd MMM yyyy")
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