package com.uinjkt.mobilepqi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.uinjkt.mobilepqi.R
import com.uinjkt.mobilepqi.data.DataMateri

class MenuMahasiswaMateriAdapter(private val dataset : List<DataMateri>) : RecyclerView.Adapter<MenuMahasiswaMateriAdapter.ItemViewHolder>() {

    private lateinit var mListener : onItemClickListener
    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener : onItemClickListener) {
        mListener = listener
    }


    inner class ItemViewHolder(view: View, listener: onItemClickListener) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.tv_judul_menu)

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuMahasiswaMateriAdapter.ItemViewHolder {
        val adapterLayout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.recycle_view_list_menu_mahasiswa, parent, false)
        return ItemViewHolder(adapterLayout,mListener)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentItem = dataset[position]
        holder.textView.text = currentItem.title_menu_name
    }

    override fun getItemCount(): Int = dataset.size
}