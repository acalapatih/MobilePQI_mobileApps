package com.uinjkt.mobilepqi.ui.kelas.daftarkelas

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mobilepqi.core.domain.model.daftarkelas.DaftarKelasModel
import com.uinjkt.mobilepqi.R
import com.uinjkt.mobilepqi.databinding.RecycleViewDaftarKelasBinding

class DaftarKelasAdapter(
    private val context: DaftarKelasActivity,
    private val dataset: List<DaftarKelasModel.DaftarKelas>,
    val listener: OnUserClickListener? = null,
) : RecyclerView.Adapter<DaftarKelasAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = RecycleViewDaftarKelasBinding.bind(view)
        fun bindItem(kelas: DaftarKelasModel.DaftarKelas) {
            binding.tvNamakelas.text = kelas.name
            binding.tvRuangkelas.text = kelas.ruang
            binding.tvJadwalkelas.text = kelas.jadwal
            binding.tvJumlahAnggotakelas.text = kelas.members.toString()

            binding.llDetailKelas.setOnClickListener {
                listener?.onUserClicked(kelas.id, "detailkelas")
            }
            binding.icDetailKelas.setOnClickListener {
                listener?.onUserClicked(kelas.id, "dashboard")
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
        fun onUserClicked(classId: Int, clicked: String)
    }

}