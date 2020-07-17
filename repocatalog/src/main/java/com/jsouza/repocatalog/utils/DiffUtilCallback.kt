package com.jsouza.repocatalog.utils

import androidx.recyclerview.widget.DiffUtil
import com.jsouza.repocatalog.data.repocatalog.remote.response.Repository

class DiffUtilCallback() : DiffUtil.ItemCallback<Repository>() {

    override fun areItemsTheSame(oldItem: Repository, newItem: Repository): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Repository, newItem: Repository): Boolean {
        return oldItem == newItem
    }
}
