package com.example.desafio_android.view.adapter.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.example.desafio_android.data.model.Item

object RepositoryDiffUtil : DiffUtil.ItemCallback<Item>() {

    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem == newItem
    }
}