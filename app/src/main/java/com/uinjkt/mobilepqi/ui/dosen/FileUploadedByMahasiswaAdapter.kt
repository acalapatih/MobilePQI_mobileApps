package com.uinjkt.mobilepqi.ui.dosen

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.uinjkt.mobilepqi.R
import com.uinjkt.mobilepqi.data.DataTugas
import com.uinjkt.mobilepqi.databinding.RecycleViewFileSentByMahasiswaBinding

class FileUploadedByMahasiswaAdapter(
    private val context : Context,
    private val dataset: MutableList<DataTugas>
) : RecyclerView.Adapter<FileUploadedByMahasiswaAdapter.ViewHolder>(){

    interface OnUserClickListener {
        fun onUserClickListener(position: Int)
    }

    inner class ViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView) {
        private val binding =  RecycleViewFileSentByMahasiswaBinding.bind(itemView)
        fun bindItem(tugasMahasiswa: DataTugas) {
            binding.tvNamaFileMahasiswaTerlampir.text = tugasMahasiswa.titleNameTugas
            binding.tvDateFileMahasiswaTerlampir.text = tugasMahasiswa.dateDeadlineTugas

            binding.ivIconCloseDownloadFile.setOnClickListener {
                Toast.makeText(context,"File Downloaded", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            LayoutInflater
            .from(context)
            .inflate(R.layout.recycle_view_file_sent_by_mahasiswa, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(dataset[position])
    }

    override fun getItemCount(): Int = dataset.size
}