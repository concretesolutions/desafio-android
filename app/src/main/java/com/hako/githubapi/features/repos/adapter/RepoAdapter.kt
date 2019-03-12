package com.hako.githubapi.features.repos.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.hako.githubapi.R
import com.hako.githubapi.domain.entities.Repository

class RepoAdapter(val onClick: (Repository) -> Unit) : ListAdapter<Repository, RepoItemViewHolder>(DiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoItemViewHolder = RepoItemViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.repo_list_item, parent, false)
    )

    override fun onBindViewHolder(holder: RepoItemViewHolder, position: Int) {
        holder.repo = getItem(position)
        holder.view.setOnClickListener { onClick(getItem(position)) }
    }
}

class DiffCallback : DiffUtil.ItemCallback<Repository>() {
    override fun areItemsTheSame(oldItem: Repository, newItem: Repository): Boolean {
        return oldItem.name == newItem.name && oldItem.description == newItem.description
    }

    override fun areContentsTheSame(oldItem: Repository, newItem: Repository): Boolean {
        return oldItem.id == newItem.id
    }
}
