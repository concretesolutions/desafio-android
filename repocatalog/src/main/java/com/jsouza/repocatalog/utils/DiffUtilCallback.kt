package com.jsouza.repocatalog.utils

import androidx.recyclerview.widget.DiffUtil
import com.jsouza.repocatalog.data.local.entity.RepositoryEntity

class DiffUtilCallback() : DiffUtil.ItemCallback<RepositoryEntity>() {

    override fun areItemsTheSame(oldItem: RepositoryEntity, newItem: RepositoryEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: RepositoryEntity, newItem: RepositoryEntity): Boolean {
        return oldItem == newItem
    }
}
