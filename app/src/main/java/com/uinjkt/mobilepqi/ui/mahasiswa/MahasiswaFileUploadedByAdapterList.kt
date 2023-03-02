package com.uinjkt.mobilepqi.ui.mahasiswa

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mobilepqi.core.domain.model.menuqiroah.GetDetailMateriQiroahModel
import com.uinjkt.mobilepqi.R
import com.uinjkt.mobilepqi.databinding.RecycleViewFileUploadedByDosenBinding

class MahasiswaFileUploadedByAdapterList(
    private val context: Context,
    private val dataset: List<Any>,
    private val setIcon: String = "download",
    private val listener: OnUserClickListener? = null
) : RecyclerView.Adapter<MahasiswaFileUploadedByAdapterList.ViewHolder>() {


    interface OnUserClickListener {
        fun onUserClickListener(action: String)
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
                    Log.d("testPrint","Print DELETE")
                    listener?.onUserClickListener("delete")
                }
            } else {
                binding.ivIconCloseOrDownloadFile.setImageResource(R.drawable.ic_download_blue)
                binding.ivIconCloseOrDownloadFile.setOnClickListener {
                    Log.d("testPrint","Print DOWNLOAD")
                    listener?.onUserClickListener("download")
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
}