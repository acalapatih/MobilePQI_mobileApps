package com.uinjkt.mobilepqi.ui.dosen

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mobilepqi.core.domain.model.tugas.GetListTugasMahasiswaModel
import com.uinjkt.mobilepqi.R
import com.uinjkt.mobilepqi.databinding.RecycleViewCekTugasMahasiswaListBinding

class ListMahasiswaAdapterNew(
    private val context : Context,
    private val dataset: List<GetListTugasMahasiswaModel.JawabanItem>,
    private val listener: OnUserClickListener? = null
) : RecyclerView.Adapter<ListMahasiswaAdapterNew.ViewHolder>() {

    interface OnUserClickListener {
        fun onUserClickListener(nim: String, status: Boolean)
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        private val binding =  RecycleViewCekTugasMahasiswaListBinding.bind(itemView)
        fun bindItem(mahasiswa: GetListTugasMahasiswaModel.JawabanItem) {
            binding.tvCekNamaMahasiswa.text = mahasiswa.name
            binding.tvCekNimMahasiswa.text = mahasiswa.nim
            Glide.with(context)
                .load(mahasiswa.avatar)
                .placeholder(R.drawable.img_user)
                .into(binding.ivProfilePictureMahasiswa)
            if(mahasiswa.status) {
                binding.clListTugasMahasiswa.setBackgroundColor(ContextCompat.getColor(context, R.color.white))
            } else {
                binding.clListTugasMahasiswa.setBackgroundColor(ContextCompat.getColor(context, R.color.blue_6199C1))
            }

            binding.root.setOnClickListener {
                listener?.onUserClickListener(mahasiswa.nim, mahasiswa.status)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ViewHolder {
        return ViewHolder(LayoutInflater
            .from(context)
            .inflate(R.layout.recycle_view_cek_tugas_mahasiswa_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(dataset[position])
    }

    override fun getItemCount(): Int = dataset.size
}