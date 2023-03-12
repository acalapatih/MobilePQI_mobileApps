package com.uinjkt.mobilepqi.ui.dosen

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mobilepqi.core.domain.model.tugas.GetJawabanForDosenModel
import com.uinjkt.mobilepqi.R
import com.uinjkt.mobilepqi.databinding.RecycleViewFileSentByMahasiswaBinding
import com.uinjkt.mobilepqi.util.convertTime
import com.uinjkt.mobilepqi.util.getFileNameFromUrl

class DosenFileUploadedByMahasiswaAdapter(
    private val context: Context,
    private val dataset: List<GetJawabanForDosenModel.Jawaban>,
    private val listener: OnUserClickListener? = null,
) : RecyclerView.Adapter<DosenFileUploadedByMahasiswaAdapter.ViewHolder>() {

    interface OnUserClickListener {
        fun onUserClickListener(position: Int)
        fun onUserDownloadClickListener(url: String)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = RecycleViewFileSentByMahasiswaBinding.bind(itemView)
        fun bindItem(tugasMahasiswa: GetJawabanForDosenModel.Jawaban) {
            val dateUploaded = tugasMahasiswa.updatedAt

            binding.tvNamaFileMahasiswaTerlampir.text = tugasMahasiswa.file.getFileNameFromUrl()
            binding.tvDateFileMahasiswaTerlampir.text =
                context.getString(
                    R.string.tv_date_file_mahasiswa_terlampir,
                    dateUploaded.convertTime( "EEEE, dd MMMM yyyy (HH:mm)")
                )

            binding.ivIconCloseDownloadFile.setOnClickListener {
                listener?.onUserDownloadClickListener(tugasMahasiswa.file)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
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