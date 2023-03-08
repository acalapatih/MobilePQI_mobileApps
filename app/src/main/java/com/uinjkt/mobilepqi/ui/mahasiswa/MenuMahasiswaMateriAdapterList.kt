package com.uinjkt.mobilepqi.ui.mahasiswa

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mobilepqi.core.domain.model.common.DataMateri
import com.uinjkt.mobilepqi.R
import com.uinjkt.mobilepqi.databinding.RecycleViewListMenuMahasiswaBinding

class MenuMahasiswaMateriAdapterList(
    private val context: Context,
    private val dataset : List<DataMateri>,
    val listener: OnUserClickListener? = null
    ) : RecyclerView.Adapter<MenuMahasiswaMateriAdapterList.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = RecycleViewListMenuMahasiswaBinding.bind(view)
        fun bindItem(materi: DataMateri, position: Int) {
            binding.tvJudulMenu.text = materi.title
            binding.cardMenu.setOnClickListener {
                listener?.onUserClicked(position)
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
        holder.bindItem(dataset[position], position)
    }

    override fun getItemCount(): Int = dataset.size

    interface OnUserClickListener {
        fun onUserClicked(position: Int)
    }
}