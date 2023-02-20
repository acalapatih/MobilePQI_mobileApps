package com.uinjkt.mobilepqi.ui.dosen

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.uinjkt.mobilepqi.R
import com.uinjkt.mobilepqi.data.DataTugasMahasiswa
import com.uinjkt.mobilepqi.databinding.RecycleViewCekTugasMahasiswaListBinding

class ListMahasiswaAdapter(
    private val context : Context,
    private val dataset: MutableList<DataTugasMahasiswa>,
    private val listener: OnUserClickListener? = null
) : RecyclerView.Adapter<ListMahasiswaAdapter.ViewHolder>() {

    interface OnUserClickListener {
        fun onUserClickListener(position: Int)
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        private val binding =  RecycleViewCekTugasMahasiswaListBinding.bind(itemView)
        val cardView = binding.root
        fun bindItem(mahasiswa: DataTugasMahasiswa) {
            binding.tvCekNamaMahasiswa.text = mahasiswa.namaMahasiswa
            binding.tvCekNimMahasiswa.text = mahasiswa.nimMahasiswa
            if(mahasiswa.doneStatus) {
                binding.clListTugasMahasiswa.setBackgroundColor(ContextCompat.getColor(context, R.color.blue_6199C1))
            } else {
                binding.clListTugasMahasiswa.setBackgroundColor(ContextCompat.getColor(context, R.color.white))
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
        holder.cardView.setOnClickListener {
            listener?.onUserClickListener(position)
        }
    }

    override fun getItemCount(): Int = dataset.size


}