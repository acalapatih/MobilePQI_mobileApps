package com.uinjkt.mobilepqi.ui.kelas.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.uinjkt.mobilepqi.R
import com.uinjkt.mobilepqi.data.DataDosen
import com.uinjkt.mobilepqi.databinding.RecycleViewDosenBinding

class DosenAdapter(
    private val context: Context,
    private val dataset: MutableList<DataDosen>
) : RecyclerView.Adapter<DosenAdapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = RecycleViewDosenBinding.bind(view)

        fun bindItem(dosen: DataDosen) {
            binding.tvNamaDosen.text = dosen.nama
            binding.tvNip.text = dosen.nip
            binding.tvNipDosen.text = dosen.nipdosen.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.recycle_view_dosen,
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