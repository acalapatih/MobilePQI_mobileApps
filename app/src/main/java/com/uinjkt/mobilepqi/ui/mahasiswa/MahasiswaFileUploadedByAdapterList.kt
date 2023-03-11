package com.uinjkt.mobilepqi.ui.mahasiswa

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mobilepqi.core.domain.model.common.FileItem
import com.uinjkt.mobilepqi.R
import com.uinjkt.mobilepqi.databinding.RecycleViewFileUploadedByDosenBinding
import com.uinjkt.mobilepqi.ui.dosen.FileUploadedUtils
import com.uinjkt.mobilepqi.util.getFileExtension
import com.uinjkt.mobilepqi.util.getFileNameFromUrl

class MahasiswaFileUploadedByAdapterList(
    private val context: Context,
    private var dataset: MutableList<FileItem>,
    private val setIcon: String,
    private val listener: OnUserClickListener? = null
) : RecyclerView.Adapter<MahasiswaFileUploadedByAdapterList.ViewHolder>() {


    interface OnUserClickListener {
        fun onUserClickListener(action: String, position: Int)
    }

    inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        private val binding = RecycleViewFileUploadedByDosenBinding.bind(view)
        private lateinit var getTypeFile: String
        private val listTypeImageFile = listOf("img","png","bmp","gif","svg","jpg", "jpeg")
        fun bindItem(listTugas : FileItem) {
            binding.tvNamaFileMahasiswaTerlampir.text = listTugas.url.getFileNameFromUrl()
            getTypeFile = listTugas.url.getFileExtension()
            when(getTypeFile) {
                in listTypeImageFile -> binding.ivLogoFileMahasiswaTerlampir.setImageResource(R.drawable.ic_image_file_type)
                else -> binding.ivLogoFileMahasiswaTerlampir.setImageResource(R.drawable.ic_documentwithtext_margined)
            }
            if (setIcon == "delete") {
                with(binding.ivIconCloseOrDownloadFile) {
                    setImageResource(R.drawable.ic_close_with_margin)
                    // Resize drawable size
                    scaleX = 1.3f
                    scaleY = 1.3f
                    setOnClickListener {
                        listener?.onUserClickListener("delete", adapterPosition)
                    }
                }
            } else {
                with(binding.ivIconCloseOrDownloadFile) {
                    setImageResource(R.drawable.ic_download_blue)
                    // Resize drawable size
                    scaleX = 1.0f
                    scaleY = 1.0f
                    setOnClickListener {
                        listener?.onUserClickListener("download", adapterPosition)
                    }
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

    fun setData(newDataset: List<FileItem>) {
        val diffUtil = FileUploadedUtils(dataset, newDataset)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        dataset = newDataset.toMutableList()
        diffResult.dispatchUpdatesTo(this)
    }
}