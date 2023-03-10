package com.uinjkt.mobilepqi.ui.mahasiswa

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mobilepqi.core.domain.model.common.TugasItem
import com.uinjkt.mobilepqi.R
import com.uinjkt.mobilepqi.databinding.RecycleViewListTugasBinding

class ListMahasiswaTugasAdapterList(
    private val context: Context,
    private val dataset: List<TugasItem>,
    private val listener: OnUserClickTugasListener? = null
) : RecyclerView.Adapter<ListMahasiswaTugasAdapterList.ViewHolder>() {

    interface OnUserClickTugasListener {
        fun onUserTugasClicked(position: Int)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = RecycleViewListTugasBinding.bind(view)
        fun bindItem(listTugas: TugasItem) {
            binding.tvNamaTaskTugas.text = listTugas.title
            binding.tvTenggatWaktuTugas.text = context.getString(R.string.tv_tenggat_waktu_tugas, listTugas.deadline?.substring(0,10))
            itemView.setOnClickListener{
                listener?.onUserTugasClicked(adapterPosition)
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
        holder.bindItem(dataset[position])
    }

    override fun getItemCount(): Int = dataset.size
}