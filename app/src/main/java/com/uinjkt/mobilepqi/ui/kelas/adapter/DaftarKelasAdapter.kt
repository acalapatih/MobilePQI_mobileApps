package com.uinjkt.mobilepqi.ui.kelas.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.uinjkt.mobilepqi.R
import com.uinjkt.mobilepqi.data.DataKelas
import com.uinjkt.mobilepqi.databinding.RecycleViewDaftarKelasBinding
import com.uinjkt.mobilepqi.ui.kelas.DaftarKelasActivity

class DaftarKelasAdapter(
    private val context: DaftarKelasActivity,
    private val dataset: MutableList<DataKelas>,
    val listener: OnUserClickListener? = null
) : RecyclerView.Adapter<DaftarKelasAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = RecycleViewDaftarKelasBinding.bind(view)
        fun bindItem(kelas: DataKelas) {
            binding.tvNamakelas.text = kelas.namaKelas
            binding.tvRuangkelas.text = kelas.ruangKelas
            binding.tvJadwalkelas.text = kelas.jadwalKelas
            binding.tvJumlahAnggotakelas.text = kelas.anggotaKelas.toString()

            binding.llDetailKelas.setOnClickListener {
                listener?.onUserClicked(kelas.idKelas-1, "anggota")
            }
            binding.icDetailKelas.setOnClickListener {
                listener?.onUserClicked(kelas.idKelas-1, "detailkelas")
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.recycle_view_daftar_kelas,
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
        fun onUserClicked(position: Int, clicked: String)
    }

}