package com.uinjkt.mobilepqi.ui.mahasiswa

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.uinjkt.mobilepqi.R
import com.uinjkt.mobilepqi.data.DataMateri
import com.uinjkt.mobilepqi.databinding.RecycleViewListMenuMahasiswaBinding

class MenuMahasiswaMateriAdapter(
    private val context: Context,
    private val dataset : List<DataMateri>,
    val listener: OnUserClickListener? = null
    ) : RecyclerView.Adapter<MenuMahasiswaMateriAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = RecycleViewListMenuMahasiswaBinding.bind(view)
        fun bindItem(materi: DataMateri) {
            binding.tvJudulMenu.text = materi.titleMenuName
            binding.cardMenu.setOnClickListener {
                listener?.onUserClicked(materi.idMateri-1)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.recycle_view_list_menu_mahasiswa,
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