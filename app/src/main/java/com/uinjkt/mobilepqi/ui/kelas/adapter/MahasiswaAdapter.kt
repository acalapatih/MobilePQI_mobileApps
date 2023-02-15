package com.uinjkt.mobilepqi.ui.kelas.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.uinjkt.mobilepqi.R
import com.uinjkt.mobilepqi.data.DataMahasiswa
import com.uinjkt.mobilepqi.databinding.RecycleViewMahasiswaBinding

class MahasiswaAdapter(
    private val context: Context,
    private val dataset: MutableList<DataMahasiswa>
) : RecyclerView.Adapter<MahasiswaAdapter.ViewHolder>(){

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = RecycleViewMahasiswaBinding.bind(view)

        fun bindItem(mahasiswa: DataMahasiswa) {
            binding.tvNamaMahasiswa.text = mahasiswa.nama
            binding.tvNim.text = mahasiswa.nim
            binding.tvNimMahasiswa.text = mahasiswa.nimmahasiswa.toString()
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