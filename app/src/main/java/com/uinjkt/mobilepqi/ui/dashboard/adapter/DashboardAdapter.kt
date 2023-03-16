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
    private val dataset: List<GetTugasModel.ListTugas>,
    val listener: OnUserClickListener? = null,
) : RecyclerView.Adapter<DashboardAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = RecycleViewTugasDashboardBinding.bind(view)
        fun bindItem(tugasDashboard: GetTugasModel.ListTugas) {
            binding.tvNamaTugas.text = tugasDashboard.title
            binding.tvLabelDeadlineTugas.text = context.getString(R.string.tv_tenggat_waktu_tugas, tugasDashboard.deadline.convertTime("dd MMMM yyyy (HH.mm)"))
            binding.tvTanggalTugas.text = tugasDashboard.createdAt.convertTime("dd MMMM yyyy")

            binding.cvTugasDashboard.setOnClickListener {
                listener?.onUserClicked(tugasDashboard.idTugas, "detailTugas")
            }
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

    interface OnUserClickListener {
        fun onUserClicked(tugasId: Int, clicked: String)
    }
}