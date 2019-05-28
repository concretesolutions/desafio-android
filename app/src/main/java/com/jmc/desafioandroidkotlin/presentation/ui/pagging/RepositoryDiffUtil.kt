package com.jmc.desafioandroidkotlin.presentation.ui.pagging

import androidx.recyclerview.widget.DiffUtil
import com.jmc.desafioandroidkotlin.presentation.model.RepositoryItem


open class RepositoryDiffUtil : DiffUtil.ItemCallback<RepositoryItem>() {
    override fun areItemsTheSame(oldItem: RepositoryItem, newItem: RepositoryItem) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: RepositoryItem, newItem: RepositoryItem) = oldItem == newItem
}