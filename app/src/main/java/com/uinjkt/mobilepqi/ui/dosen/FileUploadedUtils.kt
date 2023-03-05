package com.uinjkt.mobilepqi.ui.dosen

import androidx.recyclerview.widget.DiffUtil
import com.mobilepqi.core.domain.model.FileItem

class FileUploadedUtils(
    private val oldList: List<FileItem>,
    private val newList: List<FileItem>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] ==  newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].url ==  newList[newItemPosition].url
    }
}