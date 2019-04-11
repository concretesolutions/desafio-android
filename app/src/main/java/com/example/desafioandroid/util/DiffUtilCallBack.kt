package com.example.desafioandroid.util

import androidx.recyclerview.widget.DiffUtil
import com.example.desafioandroid.schema.RepositoryItem

class DiffUtilCallBack : DiffUtil.ItemCallback<RepositoryItem>() {
    override fun areItemsTheSame(oldItem: RepositoryItem, newItem: RepositoryItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: RepositoryItem, newItem: RepositoryItem): Boolean {
        return oldItem.id == newItem.id
                && oldItem.name == newItem.name
                && oldItem.owner == newItem.owner
                && oldItem.description == newItem.description
                && oldItem.size == newItem.size
                && oldItem.stargazersCount == newItem.stargazersCount
                && oldItem.watchersCount == newItem.watchersCount
                && oldItem.forksCount == newItem.forksCount
                && oldItem.score == newItem.score
    }

}
