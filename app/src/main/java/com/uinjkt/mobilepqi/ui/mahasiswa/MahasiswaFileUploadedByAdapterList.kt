package com.uinjkt.mobilepqi.ui.mahasiswa

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mobilepqi.core.domain.model.menuqiroah.GetDetailMateriQiroahModel
import com.uinjkt.mobilepqi.R
import com.uinjkt.mobilepqi.databinding.RecycleViewFileUploadedByDosenBinding
import com.uinjkt.mobilepqi.ui.dosen.FileUploadedUtils

class MahasiswaFileUploadedByAdapterList(
    private val context: Context,
    private var dataset: MutableList<Any>,
    private val setIcon: String = "download",
    private val listener: OnUserClickListener? = null
) : RecyclerView.Adapter<MahasiswaFileUploadedByAdapterList.ViewHolder>() {


    interface OnUserClickListener {
        fun onUserClickListener(action: String, position: Int)
    }

    inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        private val binding = RecycleViewFileUploadedByDosenBinding.bind(view)
        fun bindItem(listTugas : Any) {
            when(listTugas) {
                is GetDetailMateriQiroahModel.FileItem -> {
                    binding.tvNamaFileMahasiswaTerlampir.text =
                        listTugas.url.substring(listTugas.url.lastIndexOf('/')+1)
                }
            }
            if (setIcon == "delete") {
                binding.ivIconCloseOrDownloadFile.setImageResource(R.drawable.ic_close_delete_file)
                binding.ivIconCloseOrDownloadFile.setOnClickListener {
                    listener?.onUserClickListener("delete", adapterPosition)
                }
            } else {
                binding.ivIconCloseOrDownloadFile.setImageResource(R.drawable.ic_download_blue)
                binding.ivIconCloseOrDownloadFile.setOnClickListener {
                    listener?.onUserClickListener("download", adapterPosition)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater
                .from(context)
                .inflate(R.layout.recycle_view_file_uploaded_by_dosen, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(dataset[position])
    }

    override fun getItemCount(): Int = dataset.size

    fun setData(newDataset: List<Any>) {
        val diffUtil = FileUploadedUtils(dataset, newDataset)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        dataset.clear()
        dataset.addAll(newDataset)
        diffResult.dispatchUpdatesTo(this)
    }


}