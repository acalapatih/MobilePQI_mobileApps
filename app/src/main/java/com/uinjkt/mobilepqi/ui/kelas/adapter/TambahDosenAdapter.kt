package com.uinjkt.mobilepqi.ui.kelas.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.uinjkt.mobilepqi.R
import com.uinjkt.mobilepqi.data.DataDosen
import com.uinjkt.mobilepqi.databinding.RecycleViewTambahDosenBinding

class TambahDosenAdapter(
    private val context: Context,
    private val dataset : MutableList<DataDosen>,
    val listener: OnUserClickListener? = null
) : RecyclerView.Adapter<TambahDosenAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = RecycleViewTambahDosenBinding.bind(view)
        fun bindItem(tambahdosen: DataDosen) {
            binding.tvNamaDosen.text = tambahdosen.nama
            binding.tvNip.text = tambahdosen.nip
            binding.tvNipDosen.text = tambahdosen.nipdosen.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.recycle_view_tambah_dosen,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(dataset[position])
    }

    override fun getItemCount(): Int = dataset.size

    interface OnUserClickListener {
        fun onUserClicked(position: Int)
    }
}