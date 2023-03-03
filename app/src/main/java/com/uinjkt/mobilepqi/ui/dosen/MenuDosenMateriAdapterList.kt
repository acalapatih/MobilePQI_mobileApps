package com.uinjkt.mobilepqi.ui.dosen

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mobilepqi.core.domain.model.menuqiroah.GetMateriQiroahModel
import com.uinjkt.mobilepqi.R
import com.uinjkt.mobilepqi.databinding.RecycleViewListMenuDosenBinding

class MenuDosenMateriAdapterList(
    private val context: Context,
    private val dataset: List<GetMateriQiroahModel.DataMateri>,
    val listener: OnUserClickListener? = null
    ) : RecyclerView.Adapter<MenuDosenMateriAdapterList.ViewHolder>(){

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        private val binding =  RecycleViewListMenuDosenBinding.bind(itemView)
        fun bindItem(materi: GetMateriQiroahModel.DataMateri, position : Int) {
            binding.tvJudulMenu.text = materi.title
            binding.btnMenuDetail.setOnClickListener {
                listener?.onUserClicked(position)
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
        holder.bindItem(dataset[position], position)
    }

    override fun getItemCount(): Int = dataset.size

    interface OnUserClickListener {
        fun onUserClicked(position: Int)
    }

}