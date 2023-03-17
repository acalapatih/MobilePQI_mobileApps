package com.uinjkt.mobilepqi.ui.mahasiswa

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.mobilepqi.core.domain.model.common.JenisTugas
import com.uinjkt.mobilepqi.R
import com.uinjkt.mobilepqi.databinding.RecycleViewJenisTugasBinding

class MenuMahasiswaJenisTugasAdapterNew(
    private val context: Context,
    private val dataset: MutableList<JenisTugas>,
    private val listener: OnUserClickJenisTugasListener? = null
) : RecyclerView.Adapter<MenuMahasiswaJenisTugasAdapterNew.ViewHolder>() {

    interface OnUserClickJenisTugasListener {
        fun onUserJenisTugasClicked(data: JenisTugas)
    }

    inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        private val binding = RecycleViewJenisTugasBinding.bind(view)

        fun bindItem(jenisTugas: JenisTugas) {
            binding.btnJenisTugas.text = jenisTugas.titleJenisTugas
            if (jenisTugas.isSelected) {
                binding.btnJenisTugas.setBackgroundColor(ContextCompat.getColor(context, R.color.blue_06283d))
            } else {
                binding.btnJenisTugas.setBackgroundColor(ContextCompat.getColor(context, R.color.light_blue_0078ce))
            }
            binding.btnJenisTugas.setOnClickListener {
                changeSelected(adapterPosition)
                listener?.onUserJenisTugasClicked(jenisTugas)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater
                .from(context)
                .inflate(R.layout.recycle_view_jenis_tugas, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(dataset[position])
    }

    override fun getItemCount(): Int = dataset.size

    fun changeSelected(index: Int) {
        val listJenisTugas = dataset
        for (i in listJenisTugas.indices) {
            when (i) {
                index -> {
                    listJenisTugas[i].isSelected = true
                }
                else -> listJenisTugas[i].isSelected = false
            }
        }
        notifyItemRangeChanged(0, itemCount)
    }
}