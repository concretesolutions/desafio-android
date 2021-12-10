package com.example.desafio_android.view.adapter.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.example.desafio_android.data.model.Item
import com.example.desafio_android.data.pullmodel.RepositoryPullsItem

object PullListDiffUtil : DiffUtil.ItemCallback<RepositoryPullsItem>() {

    override fun areItemsTheSame(oldItem: RepositoryPullsItem, newItem: RepositoryPullsItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: RepositoryPullsItem, newItem: RepositoryPullsItem): Boolean {
        return oldItem == newItem
    }
}