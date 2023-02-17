package com.uinjkt.mobilepqi.ui.mahasiswa

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.uinjkt.mobilepqi.R
import com.uinjkt.mobilepqi.data.DataTugas
import com.uinjkt.mobilepqi.databinding.RecycleViewListTugasBinding

class ListMahasiswaTugasAdapter(
    private val context: Context,
    private val dataset: MutableList<DataTugas>,
    private val listener: OnUserClickTugasListener? = null
) : RecyclerView.Adapter<ListMahasiswaTugasAdapter.ViewHolder>() {

    interface OnUserClickTugasListener {
        fun onUserTugasClicked(position: Int)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = RecycleViewListTugasBinding.bind(view)
        fun bindItem(listTugas: DataTugas, position: Int) {
            if (listTugas.doneStatus){
                binding.clTaskTugas.setBackgroundColor(ContextCompat.getColor(context, R.color.white))
            }
            binding.tvNamaTaskTugas.text = listTugas.titleNameTugas
            binding.tvTenggatWaktuTugas.text = listTugas.dateDeadlineTugas
            itemView.setOnClickListener{
                listener?.onUserTugasClicked(position)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater
                .from(context)
                .inflate(R.layout.recycle_view_list_tugas, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(dataset[position], position)
    }

    override fun getItemCount(): Int = dataset.size
}