package com.uinjkt.mobilepqi.ui.kelas.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mobilepqi.core.domain.model.detailkelas.DetailKelasModel
import com.uinjkt.mobilepqi.R
import com.uinjkt.mobilepqi.databinding.RecycleViewMahasiswaBinding

class MahasiswaAdapter(
    private val context: Context,
    private val dataset: List<DetailKelasModel.ListMahasiswa>
) : RecyclerView.Adapter<MahasiswaAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = RecycleViewMahasiswaBinding.bind(view)

        fun bindItem(mahasiswa: DetailKelasModel.ListMahasiswa) {
            binding.tvNamaMahasiswa.text = mahasiswa.name
            binding.tvNim.text = R.string.tv_nim.toString()
            binding.tvNimMahasiswa.text = mahasiswa.nim
            Glide.with(context)
                .load(mahasiswa.avatar)
                .into(binding.imgProfilMahasiswa)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.recycle_view_mahasiswa,
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