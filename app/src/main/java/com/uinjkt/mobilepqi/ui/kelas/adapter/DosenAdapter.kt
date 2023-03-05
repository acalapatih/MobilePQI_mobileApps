package com.uinjkt.mobilepqi.ui.kelas.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mobilepqi.core.domain.model.detailkelas.DetailKelasModel
import com.uinjkt.mobilepqi.R
import com.uinjkt.mobilepqi.databinding.RecycleViewDosenBinding

class DosenAdapter(
    private val context: Context,
    private val dataset: List<DetailKelasModel.ListDosen>
) : RecyclerView.Adapter<DosenAdapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = RecycleViewDosenBinding.bind(view)

        fun bindItem(dosen: DetailKelasModel.ListDosen) {
            binding.tvNamaDosen.text = dosen.name
            binding.tvNipDosen.text = dosen.nip
            Glide.with(context)
                .load(dosen.avatar)
                .into(binding.imgProfilDosen)
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

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(dataset[position])
    }

    override fun getItemCount(): Int = dataset.size


}