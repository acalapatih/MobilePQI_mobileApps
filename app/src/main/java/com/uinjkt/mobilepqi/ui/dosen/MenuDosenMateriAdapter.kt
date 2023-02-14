package com.uinjkt.mobilepqi.ui.dosen

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.uinjkt.mobilepqi.R
import com.uinjkt.mobilepqi.data.DataMateri
import com.uinjkt.mobilepqi.databinding.RecycleViewListMenuDosenBinding

class MenuDosenMateriAdapter(
    private val context: Context,
    private val dataset: MutableList<DataMateri>,
    val listener: OnUserClickListener? = null
    ) : RecyclerView.Adapter<MenuDosenMateriAdapter.ViewHolder>(){

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        private val binding =  RecycleViewListMenuDosenBinding.bind(itemView)
        fun bindItem(materi: DataMateri) {
            binding.tvJudulMenu.text = materi.titleMenuName
            binding.btnMenuDetail.setOnClickListener {
                listener?.onUserClicked(materi.idMateri-1) // index = id - 1
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
      return ViewHolder(
          LayoutInflater.from(context).inflate(
              R.layout.recycle_view_list_menu_dosen,
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