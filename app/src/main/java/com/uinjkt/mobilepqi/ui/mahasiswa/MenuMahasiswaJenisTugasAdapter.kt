package com.uinjkt.mobilepqi.ui.mahasiswa

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.uinjkt.mobilepqi.R
import com.uinjkt.mobilepqi.data.DataJenisTugas
import com.uinjkt.mobilepqi.databinding.RecycleViewJenisTugasBinding

class MenuMahasiswaJenisTugasAdapter(
    private val context: Context,
    private val dataset: MutableList<DataJenisTugas>,
    private val listener: OnUserClickListener? = null
) : RecyclerView.Adapter<MenuMahasiswaJenisTugasAdapter.ViewHolder>() {

    interface OnUserClickListener {
        fun onUserClicked(position: Int)
    }

    inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        private val binding = RecycleViewJenisTugasBinding.bind(view)

        fun bindItem(jenisTugas: DataJenisTugas) {
            with(binding) {
                btnJenisTugas.text = jenisTugas.titleJenisTugas
                if (jenisTugas.statusJenisTugas) {
                    btnJenisTugas.setBackgroundColor(ContextCompat.getColor(context, R.color.blue_06283d))
                } else {
                    btnJenisTugas.setBackgroundColor(ContextCompat.getColor(context, R.color.light_blue_0078ce))
                }
                itemView.setOnClickListener {
                    listener?.onUserClicked(jenisTugas.idJenisTugas-1)
                }
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
}