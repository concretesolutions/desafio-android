package dev.theuzfaleiro.trendingongithub.ui.feature.home.adapter

import androidx.recyclerview.widget.DiffUtil
import dev.theuzfaleiro.trendingongithub.ui.feature.home.model.data.Repository

class RepositoryDiffCallback : DiffUtil.ItemCallback<Repository>() {
    override fun areItemsTheSame(oldItem: Repository, newItem: Repository) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Repository, newItem: Repository): Boolean =
        oldItem == newItem
}