package com.uinjkt.mobilepqi.ui.kelas.tambahdosen

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mobilepqi.core.domain.model.tambahdosen.TambahDosenModel
import com.uinjkt.mobilepqi.R
import com.uinjkt.mobilepqi.databinding.RecycleViewTambahDosenBinding

class TambahDosenAdapter(
    private val context: Context,
    private val dataset: List<TambahDosenModel.TambahDosen>,
    private val kuota: Int
) : RecyclerView.Adapter<TambahDosenAdapter.ViewHolder>() {

    var onDosenSelected: ((data: TambahDosenModel.TambahDosen) -> Unit)? = null

    val tempList: MutableList<TambahDosenModel.TambahDosen> = mutableListOf()

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = RecycleViewTambahDosenBinding.bind(view)
        fun bindItem(data: TambahDosenModel.TambahDosen) {
            binding.tvNamaDosen.text = data.name
            binding.tvNip.text = R.string.tv_nip.toString()
            binding.tvNipDosen.text = data.nim
            Glide.with(context)
                .load(data.avatar)
                .into(binding.imgProfilDosen)

            binding.cvTambahDosen.setOnClickListener {
                if (tempList.contains(data)) {
                    tempList.remove(data)
                    binding.icPilihDosen.isVisible = !binding.icPilihDosen.isVisible
                    onDosenSelected?.invoke(data)
                } else {
                    if (tempList.size < kuota) {
                        tempList.add(data)
                        binding.icPilihDosen.isVisible = !binding.icPilihDosen.isVisible
                        onDosenSelected?.invoke(data)
                    }
                }
            }
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
}
